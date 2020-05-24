package com.goonmeonity.domain.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccessToken {
    private String token;
    private Long expire;
}
