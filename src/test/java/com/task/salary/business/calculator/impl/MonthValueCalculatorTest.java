package com.task.salary.business.calculator.impl;

import com.task.salary.app.CalculatorApplication;
import com.task.salary.exceptions.InvalidCountryException;
import com.task.salary.exceptions.InvalidNetValueException;
import com.task.salary.exceptions.NbpException;
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
public class MonthValueCalculatorTest {

    @Autowired
    MonthValueCalculator monthValueCalculator;

    @Test
    public void calculateMonthValueAfterCostAndTaxTest() {

        BigDecimal exchange = new BigDecimal(4);
        BigDecimal dayValue = new BigDecimal(500);
        BigDecimal tax = new BigDecimal(20);
        BigDecimal cost = new BigDecimal(800);

        BigDecimal monthValue = monthValueCalculator.calculateMonthValueAfterCostAndTax(exchange, dayValue, tax, cost);

        assertEquals("Month value for 500 a day with exchange 4 and cost 800 and tax 20%",
                new Float(new BigDecimal(32000).floatValue()), new Float(monthValue.floatValue()));
    }

    @Test
    public void calculateValueForGermany500aDayTest() throws InvalidCountryException, InvalidNetValueException, NbpException {

        String dayValue = "500";
        String country = "GERMANY";

        BigDecimal monthValue = monthValueCalculator.calculateValue(dayValue, country);

        assertEquals("Month value for 500 a day in GERMANY", new Float(new BigDecimal(32000).floatValue()), new Float(monthValue.floatValue()));
    }

    @Test
    public void calculateValueForUK400aDayTest() throws InvalidCountryException, InvalidNetValueException, NbpException {

        String dayValue = "400";
        String country = "UK";

        BigDecimal monthValue = monthValueCalculator.calculateValue(dayValue, country);

        assertEquals("Month value for 400 a day in UK", new Float(new BigDecimal(30000).floatValue()), new Float(monthValue.floatValue()));
    }

    @Test(expected = InvalidCountryException.class)
    public void calculateValueInvalidCountryExceptionTest() throws InvalidCountryException, InvalidNetValueException, NbpException {

        String dayValue = "400";
        String country = "UUUUU";

        BigDecimal monthValue = monthValueCalculator.calculateValue(dayValue, country);
    }

    @Test(expected = InvalidNetValueException.class)
    public void calculateValueInvalidNetValueExceptionTest() throws InvalidCountryException, InvalidNetValueException, NbpException {

        String dayValue = "wweeddvvvrr";
        String country = "UK";

        BigDecimal monthValue = monthValueCalculator.calculateValue(dayValue, country);
    }

}
