package com.Mattheo92.ClinicProxyApp.handler.exception;

import org.springframework.http.HttpStatus;

public class DoctorNotFoundException extends ProxyClientException{
    public DoctorNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
