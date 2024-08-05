package com.Mattheo92.ClinicProxyApp.handler;

import com.Mattheo92.ClinicProxyApp.handler.exception.DoctorNotFoundException;
import com.Mattheo92.ClinicProxyApp.handler.exception.VisitNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleIllegalArgumentException(ResponseStatusException e){
        return ResponseEntity.status(e.getStatusCode()).body(e.getReason());}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<String> patientNotFoundException(DoctorNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(VisitNotFoundException.class)
    public ResponseEntity<String> visitNotFoundException(VisitNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}