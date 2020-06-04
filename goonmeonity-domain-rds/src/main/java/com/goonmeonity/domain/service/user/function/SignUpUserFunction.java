package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.user.UserRepository;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class SignUpUserFunction implements Function<User, User> {
    private UserRepository userRepository;

    @Override
    public User apply(User user) {

        return userRepository.save(user);
    }
}
