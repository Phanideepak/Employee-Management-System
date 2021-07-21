package com.licious.app.advice;

import com.licious.app.exceptions.ServiceException;
import com.licious.app.utils.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handleNoEntityFoundException(ServiceException exception){
        String message=exception.getErrorMsg();
        String errorStackTrace= exception.getErrorStackTrace();
        return ResponseBuilder.getErrorResponse(message,errorStackTrace, HttpStatus.NOT_FOUND);
    }
/*
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handleNoDataFoundException(ServiceException exception){
        String message=exception.getErrorMsg();
        String errorStackTrace=exception.getEntityName()+" records not found";
        return ResponseBuilder.getErrorResponse(message,
                errorStackTrace,HttpStatus.NOT_FOUND);
    }
*/
}
