package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.entity.user.UserDischargeInfo;
import com.goonmeonity.domain.repository.user.UserDischargeInfoRepository;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class SaveUserDischargeInfoFunction implements Function<UserDischargeInfo, UserDischargeInfo> {
    private UserDischargeInfoRepository userDischargeInfoRepository;

    @Override
    public UserDischargeInfo apply(UserDischargeInfo userDischargeInfo) {
        return userDischargeInfoRepository.save(userDischargeInfo);
    }
}

