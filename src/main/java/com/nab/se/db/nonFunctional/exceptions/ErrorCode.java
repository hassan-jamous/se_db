package com.nab.se.db.nonFunctional.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_REQUEST(0, "The request is invalid"),
    MISSING_PARAMETER(1, "Required query parameter is missing"),
    MISSING_HEADER(2, "Required header is missing"),
    UNKNOWN_EXCEPTION(3, "Unknown Exception"),
    EMPTY_RESPONSE(4, "Not found");

    private final int id;
    private final String msg;

    ErrorCode(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }
}
