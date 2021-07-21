package com.licious.app.utils;

import com.licious.app.controller.response.ErrorResponse;
import com.licious.app.controller.response.SuccessResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseBuilder {
    // write static methods..
    public static ResponseEntity getSuccessResponse(Object data, String message){
        SuccessResponse successResponse=new SuccessResponse();
        successResponse.setMessage(message);
        successResponse.setData(data);
        return new ResponseEntity(successResponse, HttpStatus.OK);
    }
    public static ResponseEntity getErrorResponse(String message,String errorStackTrace,
                                                  HttpStatus httpStatus){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setErrorStackTrace(errorStackTrace);
        return new ResponseEntity(errorResponse,httpStatus);
    }
}
