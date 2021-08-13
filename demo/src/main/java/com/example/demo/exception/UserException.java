package com.example.demo.exception;

public class UserException extends RuntimeException{
    public String message;
    public ExceptionType type;

    public enum ExceptionType {
        USER_NOT_FOUND;
    }

    public UserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
