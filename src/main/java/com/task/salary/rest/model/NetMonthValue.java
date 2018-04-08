package com.task.salary.rest.model;

public class NetMonthValue {

    private String value;
    private String errorCode;

    public NetMonthValue() {

    }

    public NetMonthValue(String value) {
        this.value = value;
    }

    public NetMonthValue(String value, String errorCode) {
        this.value = value;
        this.errorCode = errorCode;
    }

    public String getValue() {
        return value;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
