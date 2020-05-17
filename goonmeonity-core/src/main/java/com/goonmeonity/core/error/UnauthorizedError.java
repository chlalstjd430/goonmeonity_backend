package com.goonmeonity.core.error;

public class UnauthorizedError extends CanHaveStatusError {

    public UnauthorizedError(){
        this("Unauthoized Error");
    }
    public UnauthorizedError(String message) {
        super(401, message);
    }
}
