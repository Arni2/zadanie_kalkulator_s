package com.task.salary.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.task.salary.app.CalculatorApplication;
import com.task.salary.rest.model.CountryTransferObject;
import com.task.salary.rest.model.NetMonthValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class CountryControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void getMonthValueTest() throws IOException {

        String monthValueJson = getJsonFromUrl("http://localhost:" + port + "/getMonthValue/UK?dayNetValue=400");
        Gson gson = new Gson();
        NetMonthValue monthValue = gson.fromJson(monthValueJson, NetMonthValue.class);

        assertEquals("Month value for UK where day net value is 400", new Float("30000"), new Float(monthValue.getValue()));
    }

    @Test
    public void getMonthValueInvalidCountryTest() throws IOException {

        String monthValueJson = getJsonFromUrl("http://localhost:" + port + "/getMonthValue/OOO?dayNetValue=400");
        Gson gson = new Gson();
        NetMonthValue monthValue = gson.fromJson(monthValueJson, NetMonthValue.class);

        assertEquals("Message for invalid country", "INVALID_COUNTRY", monthValue.getErrorCode());
    }

    @Test
    public void getMonthValueInvalidDayNetValueTest() throws IOException {

        String monthValueJson = getJsonFromUrl("http://localhost:" + port + "/getMonthValue/GERMANY?dayNetValue=kkk");
        Gson gson = new Gson();
        NetMonthValue monthValue = gson.fromJson(monthValueJson, NetMonthValue.class);

        assertEquals("Message for invalid day net value", "INVALID_NET_VALUE_PER_DAY", monthValue.getErrorCode());
    }

    @Test
    public void getCountriesSizeTest() throws IOException {

        String countriesJson = getJsonFromUrl("http://localhost:" + port + "/getCountries");
        Gson gson = new Gson();
        List<CountryTransferObject> countries = gson.fromJson(countriesJson, List.class);

        assertEquals("Have 3 countries", 3, countries.size());
    }

    @Test
    public void getCountriesNamesTest() throws IOException {

        Type countriesListType = new TypeToken<List<CountryTransferObject>>(){}.getType();

        String countriesJson = getJsonFromUrl("http://localhost:" + port + "/getCountries");
        Gson gson = new Gson();
        List<CountryTransferObject> countries = gson.fromJson(countriesJson, countriesListType);

        int foundCountriesSize = 0;

        for(CountryTransferObject country : countries) {
            switch (country.getName()) {
                case "GERMANY":
                    ++foundCountriesSize;
                    break;
                case "POLAND":
                    ++foundCountriesSize;
                    break;
                case "UK":
                    ++foundCountriesSize;
                    break;
            }
        }

        assertEquals("Have 3 countries found", 3, foundCountriesSize);
    }

    private String getJsonFromUrl(String urlValue) throws IOException {

        String json = null;

        URL url = new URL(urlValue);
        URLConnection conn = url.openConnection();

        try (InputStream inputStream = conn.getInputStream()) {
            json = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }

        return json;
    }

}
