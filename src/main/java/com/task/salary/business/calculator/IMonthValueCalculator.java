package com.task.salary.business.calculator;

import com.task.salary.exceptions.InvalidCountryException;
import com.task.salary.exceptions.NbpException;
import com.task.salary.exceptions.InvalidNetValueException;

import java.math.BigDecimal;

public interface IMonthValueCalculator {

    public BigDecimal calculateValue(String netPerDayValue, String countryName) throws InvalidCountryException, InvalidNetValueException, NbpException;

}
