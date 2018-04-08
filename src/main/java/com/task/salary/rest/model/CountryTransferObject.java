package com.task.salary.rest.model;

public class CountryTransferObject {

    private String name;
    private String currencyCode;

    public CountryTransferObject() {

    }

    public CountryTransferObject(String name, String currencyCode) {
        this.name = name;
        this.currencyCode = currencyCode;
    }

    public String getName() {
        return name;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

}
