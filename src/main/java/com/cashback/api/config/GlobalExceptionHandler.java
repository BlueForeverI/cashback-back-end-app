package com.cashback.api.config;

import com.cashback.api.responses.ErrorResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static java.util.stream.Collectors.toList;

/**
 * Created by George on 9.5.2017 г..
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex,
                                                   HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST_400);

        String message = String.join("; ",
                ex.getBindingResult().getAllErrors().stream()
                        .map(e -> e.getDefaultMessage()).collect(toList()));

        return new ErrorResponse(message);
    }
}
