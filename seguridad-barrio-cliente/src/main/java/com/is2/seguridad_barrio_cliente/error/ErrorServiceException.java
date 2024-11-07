package com.is2.seguridad_barrio_cliente.error;

public class ErrorServiceException extends Exception{

    public ErrorServiceException() {}

    public ErrorServiceException(String msg) {
        super(msg);
    }
}