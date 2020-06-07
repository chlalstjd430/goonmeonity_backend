package com.goonmeonity.domain.service.user.function;

import com.goonmeonity.domain.repository.user.UserInstallmentSavingsRepository;
import com.goonmeonity.domain.service.user.dto.InstallmentSavingsIdAndUserId;
import lombok.AllArgsConstructor;

import java.util.function.Consumer;

@AllArgsConstructor
public class DeleteInstallmentSavingsByIdAndUserIdFunction implements Consumer<InstallmentSavingsIdAndUserId> {
    private UserInstallmentSavingsRepository userInstallmentSavingsRepository;

    @Override
    public void accept(InstallmentSavingsIdAndUserId installmentSavingsIdAndUserId) {
        userInstallmentSavingsRepository.deleteByIdAndUserId(
                installmentSavingsIdAndUserId.getInstallmentSavingsId(),
                installmentSavingsIdAndUserId.getUserId()
                );
    }
}