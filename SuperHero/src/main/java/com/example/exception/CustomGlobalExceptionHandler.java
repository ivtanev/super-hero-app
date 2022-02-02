package com.example.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HeroNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> customHandleHeroNotFound(Exception ex, WebRequest request){
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimeStamp(LocalDateTime.now());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setError(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissionNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> customHandleMissionNotFound(Exception ex, WebRequest request){
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimeStamp(LocalDateTime.now());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setError(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissionIsCompleteException.class)
    public ResponseEntity<CustomErrorResponse> customHandleMissionIsCompleteException(Exception ex, WebRequest request){
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimeStamp(LocalDateTime.now());
        errors.setStatus(HttpStatus.FORBIDDEN.value());
        errors.setError(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());

        Map<String, String> fieldErrors;
        try {
            fieldErrors = ex.getBindingResult().getFieldErrors().stream().collect(
                    Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            body.put("errors", fieldErrors);
        }catch (IllegalStateException exception){
            body.put("validation",exception.getMessage());
        }




        return new ResponseEntity<>(body, headers, status);
    }
}
