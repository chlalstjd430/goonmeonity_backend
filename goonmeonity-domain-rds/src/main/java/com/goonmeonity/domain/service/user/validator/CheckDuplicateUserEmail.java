package com.goonmeonity.domain.service.user.validator;

import com.goonmeonity.core.validator.ValidatorWithError;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.user.error.EmailIsAlreadyExistError;

public class CheckDuplicateUserEmail extends ValidatorWithError<String> {
    private UserRepository userRepository;

    public CheckDuplicateUserEmail(UserRepository userRepository) {
        super(new EmailIsAlreadyExistError());
        this.userRepository = userRepository;
    }

    @Override
    public Boolean apply(String email) {

        return !userRepository.existsByEmail(email);
    }
}
