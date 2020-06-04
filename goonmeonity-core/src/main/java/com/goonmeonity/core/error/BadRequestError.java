package com.goonmeonity.core.error;

public class BadRequestError extends CanHaveStatusError {

    public BadRequestError() {
        this("Bad Request Error");
    }
    public BadRequestError(String message){
        super(400, message);
    }
}