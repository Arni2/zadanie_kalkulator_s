package com.task.salary.tools.net.nbp.model;

import java.util.List;

public class NbpExchangeModel {

    private String table;
    private String currency;
    private String code;
    private List<NbpExchangeRate> rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<NbpExchangeRate> getRates() {
        return rates;
    }

    public void setRates(List<NbpExchangeRate> rates) {
        this.rates = rates;
    }
}
