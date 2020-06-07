package com.goonmeonity.external.api.response.user;

import com.goonmeonity.domain.service.user.dto.SimpleInstallmentSavings;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InstallmentSavingsResponse {
    private SimpleInstallmentSavings simpleInstallmentSavings;
}
