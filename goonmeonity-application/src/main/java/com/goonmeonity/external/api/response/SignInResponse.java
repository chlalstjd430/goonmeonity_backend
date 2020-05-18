package com.goonmeonity.external.api.response;

import com.goonmeonity.domain.service.user.dto.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class SignInResponse{
    private UserInfo userInfo;
    private String accessToken;
    private String refreshToken;
}
