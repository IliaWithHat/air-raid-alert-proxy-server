package org.ilia.airraidalertproxyserver.exception.handler;

import org.ilia.airraidalertproxyserver.exception.AlertNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.util.Map.entry;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AlertNotFoundException.class)
    public ResponseEntity<?> handleAlertNotFoundException(AlertNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(entry("message", ex.getMessage()));
    }
}
