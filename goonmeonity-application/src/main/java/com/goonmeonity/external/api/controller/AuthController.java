package com.goonmeonity.external.api.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @ApiOperation("테스트")
    @GetMapping("/{num}")
    @ResponseStatus(HttpStatus.OK)
    public String test(@PathVariable int num){
        return "test" + num;
    }
}
