package com.goonmeonity.core.error;

public class ConflictError extends CanHaveStatusError {

    public ConflictError() {
        this("Conflict Error");
    }
    public ConflictError(String message){
        super(409, message);
    }
}