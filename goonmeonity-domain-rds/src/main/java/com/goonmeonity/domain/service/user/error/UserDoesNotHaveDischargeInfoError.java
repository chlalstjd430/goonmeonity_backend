package com.goonmeonity.domain.service.user.error;

import com.goonmeonity.core.error.ForbiddenError;

public class UserDoesNotHaveDischargeInfoError extends ForbiddenError {
    public UserDoesNotHaveDischargeInfoError() {
        this("User does not have discharge information.");
    }

    public UserDoesNotHaveDischargeInfoError(String message) {
        super(message);
    }
}
