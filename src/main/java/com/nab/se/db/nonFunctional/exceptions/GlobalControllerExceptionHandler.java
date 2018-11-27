package com.nab.se.db.nonFunctional.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ InvalidProductTypeException.class, RequestValidationException.class })
    public ResponseEntity<ApiError> handleInvalidInput(Exception e) {
        return new ResponseEntity<>(new ApiError(ErrorCode.INVALID_REQUEST.getId(),
                ErrorCode.INVALID_REQUEST.getMsg()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ EmptyResponseException.class })
    public ResponseEntity<ApiError> handleEmptyResponse(Exception e) {
        return new ResponseEntity<>(new ApiError(ErrorCode.EMPTY_RESPONSE.getId(),
                ErrorCode.EMPTY_RESPONSE.getMsg()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ApiError> handleOtherException(Exception e) {
        return new ResponseEntity<>(new ApiError(ErrorCode.UNKNOWN_EXCEPTION.getId(),
                ErrorCode.UNKNOWN_EXCEPTION.getMsg()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
