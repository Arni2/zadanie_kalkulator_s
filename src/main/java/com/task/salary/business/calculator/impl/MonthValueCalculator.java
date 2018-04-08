package com.task.salary.business.calculator.impl;

import com.task.salary.business.calculator.IMonthValueCalculator;
import com.task.salary.exceptions.InvalidCountryException;
import com.task.salary.exceptions.InvalidNetValueException;
import com.task.salary.exceptions.NbpException;
import com.task.salary.model.CountryEntity;
import com.task.salary.service.country.ICountryService;
import com.task.salary.tools.net.nbp.INbpAverageExchangeGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MonthValueCalculator implements IMonthValueCalculator {

    @Autowired
    ICountryService countryService;

    @Autowired
    INbpAverageExchangeGetter nbpExchangeGetter;

    private static int DAYS_IN_MONTH = 22;

    public BigDecimal calculateValue(String netPerDayValue, String countryName) throws InvalidCountryException, InvalidNetValueException, NbpException {

        CountryEntity country = getCountryByName(countryName);
        String currencyCode = country.getCurrencyCode();
        BigDecimal netPerDayValueNumber = getNumberNetValuePerDay(netPerDayValue);

        BigDecimal exchange = nbpExchangeGetter.getNbpExchange(currencyCode);

        BigDecimal monthValueInPLN = calculateMonthValueAfterCostAndTax(exchange, netPerDayValueNumber, country.getTax(), country.getCost());

        return monthValueInPLN;
    }

    public BigDecimal scaleBigDecimal(BigDecimal value) {
        return value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    protected BigDecimal calculateMonthValueAfterCostAndTax(BigDecimal exchange, BigDecimal netPerDayValueNumber, BigDecimal tax, BigDecimal cost) {

        BigDecimal monthValueBeforeTaxes = netPerDayValueNumber.multiply(new BigDecimal(DAYS_IN_MONTH));
        BigDecimal monthValueTaxed = monthValueBeforeTaxes.multiply(new BigDecimal(100).subtract(tax).multiply(new BigDecimal(0.01)));
        BigDecimal monthValueAfterCostsAndTaxed = monthValueTaxed.subtract(cost);
        BigDecimal monthValueInPLN = scaleBigDecimal(monthValueAfterCostsAndTaxed.multiply(exchange));

        return monthValueInPLN;
    }

    private BigDecimal getNumberNetValuePerDay(String netPerDayValue) throws InvalidNetValueException {

        BigDecimal value = null;

        try {
            value = new BigDecimal(netPerDayValue);
        } catch(NumberFormatException e) {
            throw new InvalidNetValueException("Value: '" + netPerDayValue + "' is not a number!", e);
        }

        return value;
    }

    private CountryEntity getCountryByName(String countryName) throws InvalidCountryException {

        CountryEntity country = countryService.getCountryByName(countryName);

        if(country == null) {
            throw new InvalidCountryException("Country: " + countryName + " does not exists!");
        }

        return country;
    }

}
