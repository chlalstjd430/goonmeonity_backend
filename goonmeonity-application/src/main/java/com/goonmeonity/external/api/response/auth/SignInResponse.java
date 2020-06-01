package com.goonmeonity.external.api.response.auth;

import com.goonmeonity.domain.service.auth.dto.AccessToken;
import com.goonmeonity.domain.service.user.dto.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponse{
    private UserInfo userInfo;
    private AccessToken accessToken;
    private String refreshToken;
}
