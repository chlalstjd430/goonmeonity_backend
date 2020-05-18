package com.goonmeonity.domain.repository.user;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.ExtendRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ExtendRepository<User> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsById(long userId);
}
