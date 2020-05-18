package com.goonmeonity.domain.service.auth.error;

import com.goonmeonity.core.error.ForbiddenError;

public class TokenIsInvalidError extends ForbiddenError {
    public TokenIsInvalidError() {
        this("Token is invalid.");
    }

    public TokenIsInvalidError(String message) {
        super(message);
    }
}
