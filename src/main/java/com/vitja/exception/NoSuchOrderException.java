package com.vitja.exception;

/**
 * Created by Viktor on 15.10.2016.
 */
public class NoSuchOrderException extends RuntimeException {
    public NoSuchOrderException() { super(); }
    public NoSuchOrderException(String message) { super(message); }
    public NoSuchOrderException(String message, Throwable cause) { super(message, cause); }
    public NoSuchOrderException(Throwable cause) { super(cause); }

}
