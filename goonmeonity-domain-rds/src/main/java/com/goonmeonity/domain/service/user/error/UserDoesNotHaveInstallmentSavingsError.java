package com.goonmeonity.domain.service.user.error;

import com.goonmeonity.core.error.NotFoundError;

public class UserDoesNotHaveInstallmentSavingsError extends NotFoundError {
    public UserDoesNotHaveInstallmentSavingsError() {
        this("User does not have installment savings error.");
    }

    public UserDoesNotHaveInstallmentSavingsError(String message) {
        super(message);
    }
}
