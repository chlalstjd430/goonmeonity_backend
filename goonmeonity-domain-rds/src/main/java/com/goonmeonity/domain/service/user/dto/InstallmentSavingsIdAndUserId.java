package com.goonmeonity.domain.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InstallmentSavingsIdAndUserId {
    private long installmentSavingsId;
    private long userId;
}
