package com.goonmeonity.external.api.controller;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.external.api.error.user.UserDoesNotHavePermission;
import com.goonmeonity.external.api.request.user.CreateDischargeRequest;
import com.goonmeonity.external.api.request.user.RegisterInstallmentSavingsInfoRequest;
import com.goonmeonity.external.api.response.user.DischargeInfoResponse;
import com.goonmeonity.external.api.response.user.InstallmentSavingsInfoResponse;
import com.goonmeonity.external.api.service.AuthService;
import com.goonmeonity.external.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @ApiOperation("전역일 계산 정보 입력")
    @PostMapping("/{userId}/discharge")
    @ResponseStatus(HttpStatus.CREATED)
    public DischargeInfoResponse registerDischargeInfo(
            @RequestHeader String accessToken,
            @PathVariable long userId,
            CreateDischargeRequest createDischargeRequest
            ){
        User user = checkUserId(accessToken, userId);

        return userService.registerDischargeInfo(createDischargeRequest, user);
    }

    @ApiOperation("전역일 계산 정보 불러오기")
    @GetMapping("/{userId}/discharge")
    @ResponseStatus(HttpStatus.OK)
    public DischargeInfoResponse getDischargeInfo(@RequestHeader String accessToken,  @PathVariable long userId){
        User user = checkUserId(accessToken, userId);

        return userService.getDischargeInfo(user);
    }

    @ApiOperation("적금 정보 추가하기")
    @PostMapping("/{userId}/installment-savings")
    @ResponseStatus(HttpStatus.OK)
    public InstallmentSavingsInfoResponse registerInstallmentSavings(
            @RequestHeader String accessToken,
            @PathVariable long userId,
            RegisterInstallmentSavingsInfoRequest registerInstallmentSavingsInfoRequest
    ){
        User user = checkUserId(accessToken, userId);

        return userService.registerInstallmentSavingsInfo(user, registerInstallmentSavingsInfoRequest);
    }

    private User checkUserId(String accessToken, long userId){
        User user = authService.getUserByAccessToken(accessToken);
        if(user.getId() != userId) throw new UserDoesNotHavePermission();

        return user;
    }
}
