package com.coolReaders.library_managment_system.responses; 

import java.util.List;

public class ErrorResponse {
    List<ApiError> errors ; 

    public ErrorResponse(List<ApiError> errors)
    {
        this.errors =  errors ;
    }

    public List<ApiError> getErrors() {
        return errors;
    }
    
    
}
