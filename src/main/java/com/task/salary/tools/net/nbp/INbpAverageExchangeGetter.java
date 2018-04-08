package com.task.salary.tools.net.nbp;

import com.task.salary.exceptions.NbpException;

import java.math.BigDecimal;

public interface INbpAverageExchangeGetter {

    public BigDecimal getNbpExchange(String currency) throws NbpException;

}
