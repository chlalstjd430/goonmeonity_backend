package com.goonmeonity.external.api.request.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SignUpRequest{
    private String email;
    private String hashedPassword;
    private String nickname;
}
