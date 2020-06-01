package com.goonmeonity.external.api.controller;

import com.goonmeonity.domain.entity.board.BoardCategory;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.external.api.request.CreateBoardRequest;
import com.goonmeonity.external.api.request.SearchBoardsRequest;
import com.goonmeonity.external.api.request.UpdateBoardRequest;
import com.goonmeonity.external.api.response.BoardInfoResponse;
import com.goonmeonity.external.api.response.SearchBoardsResponse;
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
        return boardService.postBoard(user , createBoardRequest);
    }

    @ApiOperation("게시판 검색")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SearchBoardsResponse searchBoards(
            @RequestParam  BoardCategory boardCategory,
            @RequestParam(required = false, defaultValue = "")  String keyword,
            @RequestParam(required = false, defaultValue = "0")  int currentPage
    ){
        return boardService.searchBoards(
                new SearchBoardsRequest(boardCategory,keyword,currentPage)
        );
    }

    @ApiOperation("게시판 수정")
    @PutMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public BoardInfoResponse updateBoard(
            @RequestHeader String accessToken,
            @PathVariable Long boardId,
            @RequestBody UpdateBoardRequest updateBoardRequest
    ){
        User user = authService.getUserByAccessToken(accessToken);
        return boardService.updateBoard(boardId, user.getId(), updateBoardRequest);
    }
}
