package com.goonmeonity.domain.service.user.error;

import com.goonmeonity.core.error.ForbiddenError;

public class UserDoseNotExistError extends ForbiddenError {
    public UserDoseNotExistError() {
        this("User does not exist.");
    }

    public UserDoseNotExistError(String message) {
        super(message);
    }
}
