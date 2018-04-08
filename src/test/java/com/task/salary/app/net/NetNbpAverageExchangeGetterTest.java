package com.task.salary.app.net;

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

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculatorApplication.class)
@TestPropertySource(locations = "classpath:net_test.properties")
public class NetNbpAverageExchangeGetterTest {

    @Autowired
    INbpAverageExchangeGetter nbpAverageExchangeGetter;

    @Test(expected = NbpException.class)
    public void testNotExistingCurrency() throws NbpException {

        nbpAverageExchangeGetter.getNbpExchange("RRR");

    }

    @Test
    public void testExchangeForEURCurrency() throws NbpException {

        BigDecimal exchangeToPLN = nbpAverageExchangeGetter.getNbpExchange("EUR");
        assertNotEquals(new Float(0), new Float(exchangeToPLN.floatValue()));

    }

}
