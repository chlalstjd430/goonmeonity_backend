package com.goonmeonity.domain.repository.user;

import com.goonmeonity.domain.entity.user.UserDischargeInfo;
import com.goonmeonity.domain.repository.ExtendRepository;

import java.util.Optional;

public interface UserDischargeInfoRepository extends ExtendRepository<UserDischargeInfo> {
    Optional<UserDischargeInfo> findByUserId(long userId);
}
