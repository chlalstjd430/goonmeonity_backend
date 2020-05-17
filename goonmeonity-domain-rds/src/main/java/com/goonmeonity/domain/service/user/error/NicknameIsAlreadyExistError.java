package com.goonmeonity.domain.service.user.error;

import com.goonmeonity.core.error.ConflictError;

public class NicknameIsAlreadyExistError extends ConflictError {
    public NicknameIsAlreadyExistError() {
        this("Nickname is already exist.");
    }

    public NicknameIsAlreadyExistError(String message) {
        super(message);
    }
}
