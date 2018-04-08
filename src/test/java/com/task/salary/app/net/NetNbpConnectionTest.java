package com.task.salary.app.net;

import com.task.salary.app.CalculatorApplication;
import com.task.salary.exceptions.NbpException;
import com.task.salary.tools.net.nbp.impl.NbpAverageExchangeGetter;
import com.task.salary.tools.net.nbp.impl.NbpConnector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculatorApplication.class)
@TestPropertySource(locations = "classpath:net_test.properties")
public class NetNbpConnectionTest {

    @Autowired
    NbpConnector nbpConnector;

    @Autowired
    NbpAverageExchangeGetter nbpAverageExchangeGetter;

    @Test
    public void testNbpConnection() throws IOException, NbpException {

        String exchangeJson = nbpConnector.getNbpExchangeJsonFromUrl("EUR");
        BigDecimal exchangeToPLN = nbpAverageExchangeGetter.exchangeJsonToValue(exchangeJson);
        assertNotEquals(new Float(0), new Float(exchangeToPLN.floatValue()));

    }

    @Test(expected = FileNotFoundException.class)
    public void testNotExistingCurrencyNbpConnection() throws IOException, NbpException {

        String exchangeJson = nbpConnector.getNbpExchangeJsonFromUrl("EEE");

    }

}


