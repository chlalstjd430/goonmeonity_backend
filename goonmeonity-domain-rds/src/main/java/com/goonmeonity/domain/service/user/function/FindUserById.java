package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.user.UserRepository;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class FindUserById implements Function<Long, User> {
    private UserRepository userRepository;

    @Override
    public User apply(Long userId) {
        return userRepository.findById(userId).get();
    }
}
