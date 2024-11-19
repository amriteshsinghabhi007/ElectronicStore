package com.ads.electronic.store.ElectronicStore.exception;

import com.ads.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalException {
    Logger logger = LoggerFactory.getLogger(GlobalException.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder().mesaage(ex.getMessage()).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiResponseMessage,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> HandlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> allError = ex.getBindingResult().getAllErrors();
        Map<String,Object> response = new HashMap<>();
        allError.stream().forEach(objectError -> {
            String message = objectError.getDefaultMessage();
            String field = ((FieldError)objectError).getField();
            response.put(field,message);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
