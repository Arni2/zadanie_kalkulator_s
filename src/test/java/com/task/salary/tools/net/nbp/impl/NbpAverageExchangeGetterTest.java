package com.task.salary.tools.net.nbp.impl;

import com.task.salary.app.CalculatorApplication;
import com.task.salary.exceptions.NbpException;
import com.task.salary.tools.net.nbp.INbpAverageExchangeGetter;
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
public class NbpAverageExchangeGetterTest {

    @Autowired
    INbpAverageExchangeGetter nbpAverageExchangeGetter;

    @Test(expected = NbpException.class)
    public void getNbpExchangeForNotExistingCurrencyTest() throws NbpException {
        nbpAverageExchangeGetter.getNbpExchange("EEE");
    }

    @Test(expected = NbpException.class)
    public void getNbpExchangeForNotWrongJSONDataTest() throws NbpException {
        nbpAverageExchangeGetter.getNbpExchange("GGG");
    }

    @Test(expected = NbpException.class)
    public void getNbpExchangeForInvalidJSONTest() throws NbpException {
        nbpAverageExchangeGetter.getNbpExchange("JJJ");
    }

    @Test
    public void getNbpExchangeForEurTest() throws NbpException {

        BigDecimal exchange = nbpAverageExchangeGetter.getNbpExchange("EUR");
        assertEquals("Exchange for Euro is 4", new Float(4), new Float(exchange.floatValue()));

    }

    @Test
    public void getNbpExchangeForGbpTest() throws NbpException {

        BigDecimal exchange = nbpAverageExchangeGetter.getNbpExchange("GBP");
        assertEquals("Exchange for GBP is 5", new Float(5), new Float(exchange.floatValue()));

    }

}
