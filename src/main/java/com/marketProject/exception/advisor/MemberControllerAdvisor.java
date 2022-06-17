package com.marketProject.exception.advisor;

import com.marketProject.controller.member.MemberApiController;
import com.marketProject.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(assignableTypes = MemberApiController.class)
@Slf4j
public class MemberControllerAdvisor {
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorResult> incorrectEmail(InternalAuthenticationServiceException iase) {
        log.error(iase.getMessage(), iase);
        ErrorResult errorResult = ErrorResult.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(iase.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResult> incorrectPassword(BadCredentialsException bce) {
        log.error(bce.getMessage(), bce);
        ErrorResult errorResult = ErrorResult.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(bce.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
