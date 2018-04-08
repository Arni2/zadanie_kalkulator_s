package com.task.salary.tools.net.nbp.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.task.salary.exceptions.NbpException;
import com.task.salary.tools.net.nbp.INbpAverageExchangeGetter;
import com.task.salary.tools.net.nbp.INbpConnector;
import com.task.salary.tools.net.nbp.model.NbpExchangeModel;
import com.task.salary.tools.net.nbp.model.NbpExchangeRate;
import com.task.salary.utils.ConstantsValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class NbpAverageExchangeGetter implements INbpAverageExchangeGetter {

    public static final String PLN = "PLN";
    public static final BigDecimal POLISH_NO_EXCHANGE_VALUE = new BigDecimal(1);

    @Autowired
    INbpConnector nbpConnector;

    /**
     * Get middle exchange rate for given currency for this moment
     *
     * @param currency exchange currency
     * @return middle rate in PLN for given currency
     */
    public BigDecimal getNbpExchange(String currency) throws NbpException {

        BigDecimal exchange = null;

        if (PLN.equals(currency)) {
            exchange = POLISH_NO_EXCHANGE_VALUE;
        } else {

            try {

                String exchangeJson = nbpConnector.getNbpExchangeJsonFromUrl(currency);
                exchange = exchangeJsonToValue(exchangeJson);

            } catch (IOException e) {
                throw new NbpException(ConstantsValues.CONNECTION_PROBLEM_WITH_NBP_CODE, e);
            }

        }
        return exchange;
    }

    /**
     * Get exchange for given currency code in JSON<br>
     * example:<br>
     * {'table':'A','currency':'euro','code':'EUR','rates':[{'no':'068/A/NBP/2018','effectiveDate':'2018-04-06','mid':4.000}]}
     * @param exchangeJson NBP json from NBP service
     * @return exchnge for given currency
     * @throws NbpException when JSON is invalid
     */
    public BigDecimal exchangeJsonToValue(String exchangeJson) throws NbpException {

        BigDecimal exchangeValue = null;

        try {

            Gson gson = new Gson();
            NbpExchangeModel model = gson.fromJson(exchangeJson, NbpExchangeModel.class);
            List<NbpExchangeRate> rates = model.getRates();

            if (!rates.isEmpty()) {

                exchangeValue = rates.get(0).getMid();

                if (exchangeValue == null) {
                    throw new NbpException(ConstantsValues.NO_EXCHANGE_FOR_CURRENCY_CODE);
                }

            } else {
                throw new NbpException(ConstantsValues.NO_EXCHANGE_FOR_CURRENCY_CODE);
            }

        } catch (JsonSyntaxException e) {
            throw new NbpException(ConstantsValues.INVALID_NBP_JSON_CODE, e);
        } catch (Exception e) {
            throw new NbpException(ConstantsValues.UNKNOWN_NBP_JSON_CODE, e);
        }

        return exchangeValue;
    }

}
