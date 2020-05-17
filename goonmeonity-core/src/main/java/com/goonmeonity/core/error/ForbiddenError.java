package com.goonmeonity.core.error;

public class ForbiddenError extends CanHaveStatusError{

    public ForbiddenError(){
        this("Forbidden Error");
    }
    public ForbiddenError(String message) {
        super(403, message);
    }
}
