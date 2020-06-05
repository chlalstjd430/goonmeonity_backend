package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.entity.user.UserInstallmentSavings;
import com.goonmeonity.domain.repository.user.UserInstallmentSavingsRepository;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class SaveUserInstallmentSavingsFunction implements Function<UserInstallmentSavings, UserInstallmentSavings> {
    private UserInstallmentSavingsRepository userInstallmentSavingsRepository;

    @Override
    public UserInstallmentSavings apply(UserInstallmentSavings userInstallmentSavings) {
        return userInstallmentSavingsRepository.save(userInstallmentSavings);
    }
}
