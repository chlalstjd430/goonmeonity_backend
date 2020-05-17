package com.goonmeonity.core.error;

public abstract class CanHaveStatusError extends Error {
    private int status;
    private String message;

    public CanHaveStatusError(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
