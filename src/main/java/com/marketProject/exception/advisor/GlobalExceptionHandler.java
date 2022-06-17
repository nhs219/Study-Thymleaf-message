package com.marketProject.exception.advisor;

import com.marketProject.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> error(Exception e) {
        log.error(e.getMessage(), e);
        ErrorResult errorResult = ErrorResult.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errors(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResult> notFoundError(NoSuchElementException nse) {
        log.error(nse.getMessage(), nse);
        ErrorResult errorResult = ErrorResult.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .errors(nse.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }


}
