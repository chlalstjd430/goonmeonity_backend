package com.goonmeonity.domain.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputPasswordAndRealPassword {
    private String inputPassword;
    private String realPassword;
}
