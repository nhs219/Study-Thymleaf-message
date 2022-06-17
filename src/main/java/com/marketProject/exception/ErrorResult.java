package com.marketProject.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResult {
    LocalDateTime timestamp;
    int status;
    String errors;

}
