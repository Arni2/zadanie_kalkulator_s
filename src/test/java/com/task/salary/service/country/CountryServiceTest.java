package com.task.salary.service.country;

import com.task.salary.app.CalculatorApplication;
import com.task.salary.model.CountryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculatorApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class CountryServiceTest {

    @Autowired
    ICountryService countryService;

    @Test
    public void testCountryEntityForPoland() {

        CountryEntity country = countryService.getCountryByName("POLAND");
        assertEquals("3", country.getId());
        assertEquals("POLAND", country.getName());
        assertEquals("PLN", country.getCurrencyCode());
        assertEquals(new Float(1200), new Float(country.getCost().floatValue()));
        assertEquals(new Float(19), new Float(country.getTax().floatValue()));

    }

    @Test
    public void testCountryEntitySave() {

        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCost(new BigDecimal(1500));
        countryEntity.setTax(new BigDecimal(23));
        countryEntity.setCurrencyCode("EUR");
        countryEntity.setId("4");
        countryEntity.setName("FRANCE");

        countryService.save(countryEntity);

        CountryEntity country = countryService.getCountryByName("FRANCE");
        assertEquals("4", country.getId());
        assertEquals("FRANCE", country.getName());
        assertEquals("EUR", country.getCurrencyCode());
        assertEquals(new Float(1500), new Float(country.getCost().floatValue()));
        assertEquals(new Float(23), new Float(country.getTax().floatValue()));

    }

}
