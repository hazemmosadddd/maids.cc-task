package com.coolReaders.library_managment_system.exceptions.definedexceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}