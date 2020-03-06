package com.eugenpopescu.projects.atm.controller.advice;

import com.eugenpopescu.projects.atm.exception.BalanceExceededException;
import com.eugenpopescu.projects.atm.exception.CardNotFoundException;
import com.eugenpopescu.projects.atm.exception.CardPinMismatchException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CardExceptionHandler extends ResponseEntityExceptionHandler {

    public CardExceptionHandler() {
        super();
    }

    @ExceptionHandler(CardPinMismatchException.class)
    protected ResponseEntity<Object> handlePinMismatch(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Invalid PIN!", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(CardNotFoundException.class)
    protected ResponseEntity<Object> handleNumberMismatch(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Card not found!", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BalanceExceededException.class)
    protected ResponseEntity<Object> handleBalanceExceeded(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Balance exceeded!", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
