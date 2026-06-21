package me.demro.dlibs.api.exceptions;

public class APIException extends RuntimeException {
    public APIException(String message) { super(message); }
    public APIException(String message, Throwable cause) { super(message, cause); }
}