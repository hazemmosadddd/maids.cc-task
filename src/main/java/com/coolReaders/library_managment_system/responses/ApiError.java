package com.coolReaders.library_managment_system.responses; 

public class ApiError {
    private String message;
    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
