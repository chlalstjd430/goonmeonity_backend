package com.goonmeonity.domain.service.user.error;

import com.goonmeonity.core.error.NotFoundError;

public class UserNotExistError extends NotFoundError {
    public UserNotExistError() {
        this("User not found.");
    }

    public UserNotExistError(String message) {
        super(message);
    }
}
