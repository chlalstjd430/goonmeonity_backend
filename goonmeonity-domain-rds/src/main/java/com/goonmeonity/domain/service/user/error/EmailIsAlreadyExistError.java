package com.goonmeonity.domain.service.user.error;

import com.goonmeonity.core.error.ConflictError;

public class EmailIsAlreadyExistError extends ConflictError {
    public EmailIsAlreadyExistError() {
        this("User is already exist.");
    }

    public EmailIsAlreadyExistError(String message) {
        super(message);
    }
}
