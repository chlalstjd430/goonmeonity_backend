package com.goonmeonity.external.api.controller;

import com.goonmeonity.domain.service.auth.dto.AccessToken;
import com.goonmeonity.external.api.request.auth.SignInRequest;
import com.goonmeonity.external.api.request.auth.SignUpRequest;
import com.goonmeonity.external.api.response.CheckDuplicateResponse;
import com.goonmeonity.external.api.response.auth.SignInResponse;
import com.goonmeonity.external.api.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("회원가입")
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public SignInResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return authService.signUpByEmail(signUpRequest);
    }

    @ApiOperation("로그인")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse signIn(@RequestBody SignInRequest signIpRequest){

        return authService.signIn(signIpRequest);
    }

    @ApiOperation("액세스 토큰 재발급")
    @GetMapping("/access-token")
    @ResponseStatus(HttpStatus.OK)
    public AccessToken refreshAccessToken(@RequestHeader String refreshToken){
        return authService.refreshAccessToken(refreshToken);
    }

    @ApiOperation("이메일 중복 체크")
    @PostMapping("/emails/{email}/existence")
    @ResponseStatus(HttpStatus.OK)
    public CheckDuplicateResponse checkDuplicateEmail(@PathVariable String email){
        return authService.checkDuplicateEmail(email);
    }

    @ApiOperation("닉네임 중복 체크")
    @PostMapping("/nicknames/{nickname}/existence")
    @ResponseStatus(HttpStatus.OK)
    public CheckDuplicateResponse checkDuplicateNickname(@PathVariable String nickname){
        return authService.checkDuplicateNickname(nickname);
    }
}
