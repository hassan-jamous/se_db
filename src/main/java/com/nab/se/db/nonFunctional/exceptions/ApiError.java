package com.nab.se.db.nonFunctional.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
    private int errorCode;
    private String message;

    public ApiError(int id, String msg) {
        this.message = msg;
        this.errorCode = id;
    }
}
