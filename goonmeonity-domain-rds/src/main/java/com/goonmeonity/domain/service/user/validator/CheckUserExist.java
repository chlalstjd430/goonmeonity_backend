package com.goonmeonity.domain.service.user.validator;

import com.goonmeonity.core.validator.ValidatorWithError;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.user.error.UserNotExistError;

public class CheckUserExist extends ValidatorWithError<Long> {
    private UserRepository userRepository;

    public CheckUserExist(UserRepository userRepository) {
        super(new UserNotExistError());
        this.userRepository = userRepository;
    }

    @Override
    public Boolean apply(Long userId) {
        return userRepository.existsById(userId);
    }
}
