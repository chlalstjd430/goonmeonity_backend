package com.goonmeonity.external.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SignInRequest {
    private String email;
    private String hashedPassword;
}
