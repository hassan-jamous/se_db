package com.nab.se.db.nonFunctional.exceptions;

public class InvalidProductTypeException extends RuntimeException {
    public InvalidProductTypeException() {
        super();
    }
    public InvalidProductTypeException(String s) {
        super(s);
    }
    public InvalidProductTypeException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public InvalidProductTypeException(Throwable throwable) {
        super(throwable);
    }
}
