package com.task.salary.tools.net.nbp.impl;

import com.google.gson.Gson;
import com.task.salary.app.CalculatorApplication;
import com.task.salary.exceptions.NbpException;
import com.task.salary.tools.net.nbp.INbpConnector;
import com.task.salary.tools.net.nbp.model.NbpExchangeModel;
import com.task.salary.tools.net.nbp.model.NbpExchangeRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculatorApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class NbpConnectorTest {

    @Autowired
    INbpConnector nbpConnector;

    @Test
    public void testNbpConnectorJsonValues() throws IOException, NbpException {

        String exchangeJson = nbpConnector.getNbpExchangeJsonFromUrl("EUR");

        Gson gson = new Gson();
        NbpExchangeModel model = gson.fromJson(exchangeJson, NbpExchangeModel.class);

        assertEquals("EUR", model.getCode());
        assertEquals("euro", model.getCurrency());
        assertEquals("A", model.getTable());

        List<NbpExchangeRate> rates = model.getRates();
        assertEquals(1, rates.size());

        NbpExchangeRate rate = rates.get(0);
        assertEquals(new Float(4), new Float(rate.getMid().floatValue()));
        assertEquals("2018-04-06", rate.getEffectiveDate());
        assertEquals("068/A/NBP/2018", rate.getNo());

    }
}
