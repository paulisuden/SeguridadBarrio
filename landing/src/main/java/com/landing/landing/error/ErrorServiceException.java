package com.landing.landing.error;

public class ErrorServiceException extends Exception {

    public ErrorServiceException() {
    }

    public ErrorServiceException(String msg) {
        super(msg);
    }
}