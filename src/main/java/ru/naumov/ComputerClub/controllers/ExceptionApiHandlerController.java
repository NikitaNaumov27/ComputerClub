package ru.naumov.ComputerClub.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.naumov.ComputerClub.util.exceptions.BaseErrorResponse;

@RestControllerAdvice
public class ExceptionApiHandlerController {

    @ExceptionHandler
    public ResponseEntity<BaseErrorResponse> handleException(final Exception ex) {
        BaseErrorResponse response = new BaseErrorResponse(
                ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
