package com.task.salary.rest;

import com.task.salary.business.calculator.IMonthValueCalculator;
import com.task.salary.exceptions.InvalidCountryException;
import com.task.salary.exceptions.InvalidNetValueException;
import com.task.salary.exceptions.NbpException;
import com.task.salary.model.CountryEntity;
import com.task.salary.rest.model.CountryTransferObject;
import com.task.salary.rest.model.NetMonthValue;
import com.task.salary.service.country.ICountryService;
import com.task.salary.utils.ConstantsValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    private static Logger LOGGER = Logger.getLogger(CountryController.class.getName());

    private static final String RETURNED_VALUE_ON_ERROR = "0";

    @Autowired
    ICountryService countryService;

    @Autowired
    IMonthValueCalculator calculator;

    @RequestMapping(value = "/getMonthValue/{country}")
    public NetMonthValue calculateNetMonthValue(@PathVariable("country") String country, @RequestParam("dayNetValue") String netPerDayValue) {

        NetMonthValue result = null;

        try {

            BigDecimal calculatedValue = calculator.calculateValue(netPerDayValue, country);
            result = new NetMonthValue(calculatedValue.toString());

        } catch (InvalidCountryException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            result = new NetMonthValue(RETURNED_VALUE_ON_ERROR, ConstantsValues.INVALID_COUNTRY_CODE);
        } catch (InvalidNetValueException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            result = new NetMonthValue(RETURNED_VALUE_ON_ERROR, ConstantsValues.INVALID_NET_VALUE_PER_DAY_CODE);
        } catch (NbpException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            result = new NetMonthValue(RETURNED_VALUE_ON_ERROR, ConstantsValues.INVALID_NBP_EXCHANGE_CODE);
        }

        return result;
    }

    @RequestMapping(value = "/getCountries")
    public List<CountryTransferObject> getCountries() {

        List<CountryEntity> entities = countryService.getCountries();
        List<CountryTransferObject> transferObjects = entities.stream()
                                                              .map(entity -> new CountryTransferObject(entity.getName(), entity.getCurrencyCode()))
                                                              .collect(Collectors.toList());
        return transferObjects;

    }
}
