package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.entity.user.UserInstallmentSavings;
import com.goonmeonity.domain.repository.user.UserInstallmentSavingsRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class FindUserInstallmentSavingsByUserIdFunction implements Function<Long, List<UserInstallmentSavings>> {
    private UserInstallmentSavingsRepository userInstallmentSavingsRepository;

    @Override
    public List<UserInstallmentSavings> apply(Long userId) {
        return userInstallmentSavingsRepository.findAllByUserId(userId);
    }
}
