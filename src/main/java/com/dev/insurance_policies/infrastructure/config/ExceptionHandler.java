package com.dev.insurance_policies.infrastructure.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity httpClientException(HttpClientErrorException ex) {
        if (ex.getStatusCode().equals(404)) {
            return ResponseEntity.status(404).body("Error: Recurso no encontrado.");
        } else if (ex.getStatusCode().equals(400)) {
            return ResponseEntity.status(400).body("Error: Petición incorrecta.");
        } else if (ex.getStatusCode().equals(500)) {
            return ResponseEntity.status(500).body("Error: Error interno del servidor.");
        } else {
            return ResponseEntity.status(ex.getStatusCode()).body("Error: " + ex.getMessage());
        }
    }


}