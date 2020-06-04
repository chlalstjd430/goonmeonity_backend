package com.goonmeonity.external.api.error;

import com.goonmeonity.core.error.BadRequestError;

public class ClientRequestIsInvalidError extends BadRequestError {
    public ClientRequestIsInvalidError() {
        this("The client's request is invalid.");
    }

    public ClientRequestIsInvalidError(String message) {
        super(message);
    }
}