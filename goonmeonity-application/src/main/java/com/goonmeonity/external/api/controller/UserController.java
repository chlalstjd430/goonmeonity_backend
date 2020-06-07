package com.goonmeonity.external.api.controller;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.external.api.error.user.UserDoesNotHavePermission;
import com.goonmeonity.external.api.request.user.CreateDischargeRequest;
import com.goonmeonity.external.api.request.user.RegisterInstallmentSavingsRequest;
import com.goonmeonity.external.api.response.user.DeleteInstallmentSavingsResponse;
import com.goonmeonity.external.api.response.user.DischargeInfoResponse;
import com.goonmeonity.external.api.response.user.InstallmentSavingsListResponse;
import com.goonmeonity.external.api.response.user.InstallmentSavingsResponse;
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
    public InstallmentSavingsResponse registerInstallmentSavings(
            @RequestHeader String accessToken,
            @PathVariable long userId,
            RegisterInstallmentSavingsRequest registerInstallmentSavingsRequest
    ){
        User user = checkUserId(accessToken, userId);

        return userService.registerInstallmentSavings(user, registerInstallmentSavingsRequest);
    }

    @ApiOperation("적금 정보 리스트 조회")
    @GetMapping("/{userId}/installment-savings")
    @ResponseStatus(HttpStatus.OK)
    public InstallmentSavingsListResponse getInstallmentSavingsList(@RequestHeader String accessToken, @PathVariable long userId){
        User user = checkUserId(accessToken, userId);

        return userService.getInstallmentSavingsList(user);
    }

    @ApiOperation("적금 정보 조회")
    @GetMapping("/{userId}/installment-savings/{installmentSavingsId}")
    @ResponseStatus(HttpStatus.OK)
    public InstallmentSavingsResponse getInstallmentSavings(
            @RequestHeader String accessToken,
            @PathVariable long userId,
            @PathVariable long installmentSavingsId
    ){
        User user = checkUserId(accessToken, userId);

        return userService.getInstallmentSavings(user, installmentSavingsId);
    }

    @ApiOperation("적급 정보 삭제")
    @DeleteMapping("/{userId}/installment-savings/{installmentSavingsId}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteInstallmentSavingsResponse deleteInstallmentSavings(
            @RequestHeader String accessToken,
            @PathVariable long userId,
            @PathVariable long installmentSavingsId
    ){
        User user = checkUserId(accessToken, userId);

        return userService.deleteInstallmentSavings(user, installmentSavingsId);
    }

    private User checkUserId(String accessToken, long userId){
        User user = authService.getUserByAccessToken(accessToken);
        if(user.getId() != userId) throw new UserDoesNotHavePermission();

        return user;
    }
}
