package com.Mattheo92.ClinicProxyApp.decoder;

import com.Mattheo92.ClinicProxyApp.exception.ExceptionMessage;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Date;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 503) {
            String message = "Sorry, but service is unavailable now";
            try (InputStream bodyIs = response.body().asInputStream()) {
                ObjectMapper mapper = new ObjectMapper();
                ExceptionMessage exceptionMessage = mapper.readValue(bodyIs, ExceptionMessage.class);
                if (exceptionMessage != null && exceptionMessage.getMessage() != null) {
                    message = exceptionMessage.getMessage();
                }
            } catch (IOException e) {
                return new Exception(e.getMessage());
            }
            return new RetryableException(
                    response.status(),
                    message,
                    response.request().httpMethod(),
                    Date.from(Instant.now().plusMillis(5000)),
                    response.request());
        } 
        
            return errorDecoder.decode(methodKey, response);
    }
}