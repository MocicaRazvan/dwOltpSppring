package com.mocicarazvan.dwoltp.advices;


import com.mocicarazvan.dwoltp.dtos.exceptions.EnitityWithUniqueExistsExceptionResponse;
import com.mocicarazvan.dwoltp.dtos.exceptions.ExceptionResponse;
import com.mocicarazvan.dwoltp.dtos.exceptions.NotFoundExceptionResponse;
import com.mocicarazvan.dwoltp.dtos.exceptions.ValidationExceptionResponse;
import com.mocicarazvan.dwoltp.exceptions.EntityWithUniqueExists;
import com.mocicarazvan.dwoltp.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BaseAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> validationError(MethodArgumentNotValidException error, WebRequest request) {


        return ResponseEntity.badRequest().body(ValidationExceptionResponse.fromExceptionResponse(
                respMapWithMessage(HttpStatus.BAD_REQUEST, error, request),
                error.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, f -> Objects.requireNonNullElse(f.getDefaultMessage(), ""))
                        )));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionResponse> notFoundError(NotFoundException error, WebRequest request) {
        return new ResponseEntity<>(NotFoundExceptionResponse.fromExceptionResponse(
                respMapWithMessage(HttpStatus.NOT_FOUND, error, request), error
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityWithUniqueExists.class)
    public ResponseEntity<EnitityWithUniqueExistsExceptionResponse> entityWithUniqueExistsError(EntityWithUniqueExists error, WebRequest request) {
        return new ResponseEntity<>(EnitityWithUniqueExistsExceptionResponse.fromExceptionResponse(
                respMapWithMessage(HttpStatus.CONFLICT, error, request), error
        ), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> genericError(Exception error, WebRequest request) {
        return handleWithMessage(HttpStatus.INTERNAL_SERVER_ERROR, error, request);
    }

    protected ResponseEntity<ExceptionResponse> handleWithMessage(HttpStatus status, Exception error, WebRequest request) {
        return new ResponseEntity<>(respMapWithMessage(status, error, request), status);
    }

    protected ExceptionResponse respMapWithMessage(HttpStatus status, Exception error, WebRequest request) {
        return ExceptionResponse.builder()
                .message(error.getMessage())
                .timestamp(Instant.now().toString())
                .reason(status.getReasonPhrase())
                .status(status.value())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
    }


}
