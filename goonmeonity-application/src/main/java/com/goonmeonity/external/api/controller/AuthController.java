package com.goonmeonity.external.api.controller;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.service.user.dto.UserInfo;
import com.goonmeonity.external.api.request.SignUpRequest;
import com.goonmeonity.external.api.response.SignInResponse;
import com.goonmeonity.external.api.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation("회원가입")
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public UserInfo signUp(@RequestBody SignUpRequest signUpRequest){
        return authService.signUpByEmail(signUpRequest);
    }

    @ApiOperation("로그인")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public String signUp(){

        return "test";
    }

    @ApiOperation("액세스 토큰 재발급")
    @PostMapping("/access-token")
    @ResponseStatus(HttpStatus.OK)
    public String refreshAccessToken(){

        return "test";
    }
}
