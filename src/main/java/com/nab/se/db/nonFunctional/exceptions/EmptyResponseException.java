package com.nab.se.db.nonFunctional.exceptions;

public class EmptyResponseException extends RuntimeException {
    public EmptyResponseException() {
        super();
    }
    public EmptyResponseException(String s) {
        super(s);
    }
    public EmptyResponseException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public EmptyResponseException(Throwable throwable) {
        super(throwable);
    }
}
