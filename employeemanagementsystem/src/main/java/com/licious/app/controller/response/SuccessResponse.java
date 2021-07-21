package com.licious.app.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse {

    private Object data;
    private String message;


}
