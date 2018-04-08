package com.task.salary.tools.net.nbp.impl;

import com.task.salary.exceptions.NbpException;
import com.task.salary.tools.net.nbp.INbpConnector;
import com.task.salary.utils.ConstantsValues;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

@Service
@Profile({"dev", "net_test"})
public class NbpConnector implements INbpConnector {

    public String getNbpExchangeJsonFromUrl(String currency) throws FileNotFoundException, IOException, NbpException {

        String exchangeJson = null;

        URL url = new URL(getUrlForCurrency(currency));
        URLConnection conn = url.openConnection();

        try (InputStream inputStream = conn.getInputStream()) {
            exchangeJson = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }

        return exchangeJson;
    }

    private String getUrlForCurrency(String currency) {

        StringBuilder builder = new StringBuilder();
        builder.append(ConstantsValues.NBP_URL_PREFIX);
        builder.append(currency.toLowerCase());
        builder.append(ConstantsValues.NBP_JSON_SUFFIX);

        return builder.toString();
    }

}
