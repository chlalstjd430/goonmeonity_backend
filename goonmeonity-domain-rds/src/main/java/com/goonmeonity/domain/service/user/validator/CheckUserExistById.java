package com.goonmeonity.domain.service.user.validator;

import com.goonmeonity.core.validator.ValidatorWithError;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.user.error.UserDoseNotExistError;

public class CheckUserExistById extends ValidatorWithError<Long> {
    private UserRepository userRepository;

    public CheckUserExistById(UserRepository userRepository) {
        super(new UserDoseNotExistError());
        this.userRepository = userRepository;
    }

    @Override
    public Boolean apply(Long userId) {
        return userRepository.existsById(userId);
    }
}
