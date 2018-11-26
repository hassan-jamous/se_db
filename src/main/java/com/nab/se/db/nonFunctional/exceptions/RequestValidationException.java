package com.nab.se.db.nonFunctional.exceptions;

public class RequestValidationException extends RuntimeException {
    public RequestValidationException() {
        super();
    }
    public RequestValidationException(String s) {
        super(s);
    }
    public RequestValidationException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public RequestValidationException(Throwable throwable) {
        super(throwable);
    }
}
