package com.licious.app.controller.response;

import lombok.Data;

import java.util.List;
@Data
public class ErrorResponse {
    private String message;
    private String errorStackTrace;
}
