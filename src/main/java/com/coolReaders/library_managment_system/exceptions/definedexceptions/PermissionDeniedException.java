package com.coolReaders.library_managment_system.exceptions.definedexceptions;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String message) {
        super(message);
    }
}