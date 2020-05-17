package com.goonmeonity.core.error;

public class NotFoundError extends CanHaveStatusError {

    public  NotFoundError(){
        this("NotFound Error");
    }
    public NotFoundError(String message) {
        super(404, message);
    }
}
