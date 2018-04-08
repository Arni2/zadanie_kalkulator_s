package com.task.salary.exceptions;

public class InvalidCountryException extends Exception {

    public InvalidCountryException(String code) {
        super(code);
    }

    public InvalidCountryException(String code, Throwable throwable) {
        super(code, throwable);
    }

}
