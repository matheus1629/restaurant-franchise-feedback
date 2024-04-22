package com.restaurant.feedbacksanalysis.exception;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.toList());

        Map<String, Object> body = createResponseBody((HttpStatus) status, errors.get(0).toString());

        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (!(ex.getCause() instanceof JsonMappingException)) {
            return new ResponseEntity("", headers, status);
        }

        JsonMappingException e = (JsonMappingException) ex.getCause();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        List<String> messages = new ArrayList<>();

        for (JsonMappingException.Reference reference : e.getPath()) {
            String fieldName = reference.getFieldName();
            messages.add("Invalid field: " + fieldName);
        }

        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) ex.getCause();
            Class<?> targetType = ife.getTargetType();
            if (targetType.isEnum()) {
                String enumValues = Arrays.toString(targetType.getEnumConstants());
                messages.add("Accepted values: " + enumValues);
            }
        }

        body.put("messages", messages);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        Map<String, Object> body = createResponseBody(HttpStatus.BAD_REQUEST, errors.get(0));

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(TimeoutException.class)
    protected ResponseEntity<Object> handleTimeout(
            TimeoutException ex, WebRequest request) {

        String error = "The request exceeded the maximum allowed time of 5 seconds.";

        Map<String, Object> body = createResponseBody(HttpStatus.REQUEST_TIMEOUT, error);

        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(body);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Object> handleException(BusinessRuleException exception) {

        Map<String, Object> body = createResponseBody(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    private Map<String, Object> createResponseBody(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("message", message);
        return body;
    }
}
