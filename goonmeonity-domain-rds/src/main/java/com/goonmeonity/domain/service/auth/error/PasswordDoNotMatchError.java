package com.goonmeonity.domain.service.auth.error;

import com.goonmeonity.core.error.UnauthorizedError;

public class PasswordDoNotMatchError extends UnauthorizedError {
    public PasswordDoNotMatchError() {
        this("Password do not match.");
    }

    public PasswordDoNotMatchError(String message) {
        super(message);
    }
}
