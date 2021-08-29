package com.restboilarplate.exception;


import com.restboilarplate.entity.exception.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@ResponseStatus
@SuppressWarnings({"unchecked","rawtypes"})
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**Exception Handing for Not Found Exception*/
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception,
                                                          WebRequest request) {
        List<String> details = new ArrayList<>();
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,
                exception.getMessage(),details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    /**Exception Handing for CustomException*/
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorMessage> customException(CustomException exception,
                                                        WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,
                exception.getMessage(),details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
                "Server Error",details);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,
                "Validation Failed",details);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }


}
