package com.Mattheo92.ClinicProxyApp.handler.exception;

import org.springframework.http.HttpStatus;

public class ProxyClientException extends RuntimeException{
    private final HttpStatus status;

    public ProxyClientException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
