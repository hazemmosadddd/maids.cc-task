package com.coolReaders.library_managment_system.exceptions.definedexceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}