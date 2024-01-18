package com.accenture.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        return new ResponseEntity<>(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getTitleMessageCode())
                , HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFoundException(EntityNotFoundException e) {

        return new ResponseEntity<>(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage())
                , HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CustomDataAccessException.class)
    public ResponseEntity<ProblemDetail> handleDataAccessException(CustomDataAccessException e) {

        return new ResponseEntity<>(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage())
                , HttpStatus.BAD_REQUEST);
    }
}
