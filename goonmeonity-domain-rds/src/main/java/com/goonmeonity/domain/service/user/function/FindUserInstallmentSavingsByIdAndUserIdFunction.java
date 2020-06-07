package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.entity.user.UserInstallmentSavings;
import com.goonmeonity.domain.repository.user.UserInstallmentSavingsRepository;
import com.goonmeonity.domain.service.user.dto.InstallmentSavingsIdAndUserId;
import com.goonmeonity.domain.service.user.error.UserDoesNotHaveInstallmentSavingsError;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class FindUserInstallmentSavingsByIdAndUserIdFunction implements Function<InstallmentSavingsIdAndUserId, UserInstallmentSavings> {
    private UserInstallmentSavingsRepository userInstallmentSavingsRepository;

    @Override
    public UserInstallmentSavings apply(InstallmentSavingsIdAndUserId installmentSavingsIdAndUserId) {
        return userInstallmentSavingsRepository.findByIdAndUserId(
                installmentSavingsIdAndUserId.getInstallmentSavingsId(),
                installmentSavingsIdAndUserId.getUserId()
                ).orElseThrow(UserDoesNotHaveInstallmentSavingsError::new);
    }
}
