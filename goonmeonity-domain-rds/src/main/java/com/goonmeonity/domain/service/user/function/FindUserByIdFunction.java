package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.user.error.UserDoseNotExistError;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class FindUserByIdFunction implements Function<Long, User> {
    private UserRepository userRepository;

    @Override
    public User apply(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserDoseNotExistError::new);
    }
}
