package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.user.error.UserDoseNotExistError;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class FindUserByEmail implements Function<String, User> {
    private UserRepository userRepository;

    @Override
    public User apply(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserDoseNotExistError::new);
    }
}
