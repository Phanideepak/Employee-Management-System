package com.licious.app.exceptions;

import lombok.Getter;

public class ServiceException extends RuntimeException{
    @Getter
    private String errorMsg;
    @Getter
    private String entityName;
    @Getter
    private String errorStackTrace;
    public ServiceException(){
        super();
    }
    public ServiceException(String entityName, int id){
        super(entityName+" with id="+id+" has not found");
        this.errorMsg=entityName+" with id="+id+" has not found";
        this.entityName=entityName;
        this.errorStackTrace=entityName+" not found";
    }
    public ServiceException(String entityName){
        super(entityName+" records has not found");
        this.errorMsg=entityName+" records has not found";
        this.entityName=entityName;
        this.errorStackTrace=entityName+" records not found";
    }

    public ServiceException(String message,String crudOperation){
          super(message);
          this.errorMsg=message;
          this.errorStackTrace=crudOperation+" Operation failed";
    }
}
