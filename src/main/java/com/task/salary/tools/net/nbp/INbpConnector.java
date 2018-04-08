package com.task.salary.tools.net.nbp;

import com.task.salary.exceptions.NbpException;

import java.io.IOException;

public interface INbpConnector {

    public String getNbpExchangeJsonFromUrl(String currency) throws IOException, NbpException;

}
