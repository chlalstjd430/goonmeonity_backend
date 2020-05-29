package com.goonmeonity.external.api.controller;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.external.api.request.CreateBoardRequest;
import com.goonmeonity.external.api.request.SignUpRequest;
import com.goonmeonity.external.api.response.BoardInfoResponse;
import com.goonmeonity.external.api.response.SignInResponse;
import com.goonmeonity.external.api.service.AuthService;
import com.goonmeonity.external.api.service.BoardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/boards")
public class BoardController {
    private BoardService boardService;
    private AuthService authService;

    public BoardController(BoardService boardService, AuthService authService) {
        this.boardService = boardService;
        this.authService = authService;
    }


    @ApiOperation("게시판 작성")
    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardInfoResponse writeBoard(
            @RequestHeader String accessToken,
            @RequestBody CreateBoardRequest createBoardRequest
    ){
        User user = authService.getUserByAccessToken(accessToken);
        return boardService.create(user , createBoardRequest);
    }
}
