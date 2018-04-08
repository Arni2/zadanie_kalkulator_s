package com.task.salary.exceptions;

public class InvalidNetValueException extends Exception {

    public InvalidNetValueException(String code) {
        super(code);
    }

    public InvalidNetValueException(String code, Throwable throwable) {
        super(code, throwable);
    }

}
