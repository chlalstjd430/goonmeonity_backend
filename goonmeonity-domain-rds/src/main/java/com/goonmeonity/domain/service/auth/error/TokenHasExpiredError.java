package com.goonmeonity.domain.service.auth.error;

import com.goonmeonity.core.error.ForbiddenError;

public class TokenHasExpiredError extends ForbiddenError {
    public TokenHasExpiredError() {
        this("Token has expired");
    }

    public TokenHasExpiredError(String message) {
        super(message);
    }
}
