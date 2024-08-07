package com.coolReaders.library_managment_system.responses; 

public class ApiResponse<T> {
    private String message;
    private T data;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
    public T getData() {
        return data;
    }
    public String getMessage() {
        return message;
    }
}
