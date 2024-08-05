package com.Mattheo92.ClinicProxyApp.handler.exception;

import org.springframework.http.HttpStatus;

public class VisitNotFoundException extends ProxyClientException {
    public VisitNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
