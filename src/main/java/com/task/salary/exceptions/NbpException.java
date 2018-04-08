package com.task.salary.exceptions;

public class NbpException extends Exception {

    public NbpException(String code) {
        super(code);
    }

    public NbpException(String code, Throwable throwable) {
        super(code, throwable);
    }

}
