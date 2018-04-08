package com.task.salary.tools.net.nbp.impl;

import com.task.salary.exceptions.NbpException;
import com.task.salary.tools.net.nbp.INbpConnector;
import com.task.salary.utils.ConstantsValues;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile(value = "test")
public class NbpConnectorMock implements INbpConnector {

    public String getNbpExchangeJsonFromUrl(String currency) throws IOException, NbpException {

        String exchangeJson = null;

        switch (currency) {
            case "EUR":
                exchangeJson = "{'table':'A','currency':'euro','code':'EUR','rates':[{'no':'068/A/NBP/2018','effectiveDate':'2018-04-06','mid':4.000}]}";
                break;
            case "GBP":
                exchangeJson = "{'table':'A','currency':'funt szterling','code':'GBP','rates':[{'no':'068/A/NBP/2018','effectiveDate':'2018-04-06','mid':5.000}]}";
                break;
            case "GGG":
                // valid JSON, but no mid value
                exchangeJson = "{'table':'A','currency':'funt szterling','code':'GBP','rates':[{'no':'068/A/NBP/2018','effectiveDate':'2018-04-06','mmm':6.000}]}";
                break;
            case "JJJ":
                // invalid JSON
                exchangeJson = "{'tablecyfunt szterling','code':'GBP','rates':[{'no':'068/A/NBP/2018','effectiveDate':'2018-04-06','mid':6.000}]}";
                break;
            default:
                throw new NbpException(ConstantsValues.INVALID_CURRENCY_CODE);
        }

        return exchangeJson;
    }
}
