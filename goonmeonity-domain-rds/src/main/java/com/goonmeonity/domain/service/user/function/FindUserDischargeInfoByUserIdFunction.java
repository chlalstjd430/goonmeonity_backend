package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.entity.user.UserDischargeInfo;
import com.goonmeonity.domain.repository.user.UserDischargeInfoRepository;
import com.goonmeonity.domain.service.user.error.UserDoesNotHaveDischargeInfoError;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class FindUserDischargeInfoByUserIdFunction implements Function<Long, UserDischargeInfo> {
    private UserDischargeInfoRepository userDischargeInfoRepository;

    @Override
    public UserDischargeInfo apply(Long userId) {
        return userDischargeInfoRepository.findByUserId(userId).orElseThrow(UserDoesNotHaveDischargeInfoError::new);
    }
}
