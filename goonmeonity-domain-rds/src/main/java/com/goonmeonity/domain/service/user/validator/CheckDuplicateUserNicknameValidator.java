package com.goonmeonity.domain.service.user.validator;

import com.goonmeonity.core.validator.ValidatorWithError;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.user.error.NicknameIsAlreadyExistError;

public class CheckDuplicateUserNicknameValidator extends ValidatorWithError<String> {
    private UserRepository userRepository;

    public CheckDuplicateUserNicknameValidator(UserRepository userRepository) {
        super(new NicknameIsAlreadyExistError());
        this.userRepository = userRepository;
    }

    @Override
    public Boolean apply(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }
}

