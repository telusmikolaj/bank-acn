package com.accenture.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Slf4j
public class EntityNotFoundException  extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
