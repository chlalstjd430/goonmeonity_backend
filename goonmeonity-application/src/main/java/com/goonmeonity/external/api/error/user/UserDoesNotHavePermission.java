package com.goonmeonity.external.api.error.user;

import com.goonmeonity.core.error.UnauthorizedError;

public class UserDoesNotHavePermission extends UnauthorizedError {
    public UserDoesNotHavePermission() {
        this("User does not have permission.");
    }

    public UserDoesNotHavePermission(String message) {
        super(message);
    }
}

