package com.ghtk.productmanagement.exceptions;

import com.ghtk.productmanagement.models.responses.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<CommonResponse> handleNotFoundException(EntityNotFoundException e) {
        CommonResponse response = CommonResponse.builder()
                .success(false)
                .message("Not Found")
                .data(null)
                .errors(getErrors(e))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ParseException.class})
    public ResponseEntity<CommonResponse> handleParseException(ParseException e) {
        CommonResponse response = CommonResponse.builder()
                .success(false)
                .message("Not Found")
                .data(null)
                .errors(getErrors(e))
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        CommonResponse response = CommonResponse.builder()
                .success(false)
                .message("Data validation failed")
                .data(null)
                .errors(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleUnwantedException(Exception e) {

        CommonResponse response = CommonResponse.builder()
                .success(false)
                .message("Unknown error")
                .data(null)
                .errors(getErrors(e))
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private List<String> getErrors(Exception e){
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return errors;
    }
}
