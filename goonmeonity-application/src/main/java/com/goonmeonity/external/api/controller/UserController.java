package com.goonmeonity.external.api.controller;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.external.api.error.user.UserDoesNotHavePermission;
import com.goonmeonity.external.api.request.user.CreateDischargeRequest;
import com.goonmeonity.external.api.response.user.CreateDischargeResponse;
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
    public CreateDischargeResponse createComment(
            @RequestHeader String accessToken,
            @PathVariable long userId,
            @RequestBody CreateDischargeRequest createDischargeRequest
            ){
        User user = authService.getUserByAccessToken(accessToken);
        if(user.getId() != userId) throw new UserDoesNotHavePermission();

        return userService.registerDischargeInfo(createDischargeRequest, user);
    }
}
