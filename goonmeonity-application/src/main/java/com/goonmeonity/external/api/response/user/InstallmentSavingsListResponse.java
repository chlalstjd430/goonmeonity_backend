package com.goonmeonity.external.api.response.user;

import com.goonmeonity.domain.service.user.dto.SimpleInstallmentSavings;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class InstallmentSavingsListResponse {
    List<SimpleInstallmentSavings> simpleInstallmentSavingsList;
}
