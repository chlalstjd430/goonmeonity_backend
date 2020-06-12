package com.goonmeonity.external.api.controller;

import com.goonmeonity.domain.entity.board.BoardCategory;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.external.api.request.board.CreateBoardRequest;
import com.goonmeonity.external.api.request.board.SearchBoardsRequest;
import com.goonmeonity.external.api.request.board.UpdateBoardRequest;
import com.goonmeonity.external.api.response.board.BoardInfoResponse;
import com.goonmeonity.external.api.response.board.BoardRecommendationResponse;
import com.goonmeonity.external.api.response.board.DeleteBoardResponse;
import com.goonmeonity.external.api.response.board.SearchBoardsResponse;
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

    @ApiOperation("게시글 작성")
    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardInfoResponse writeBoard(
            @RequestHeader String accessToken,
            @RequestBody CreateBoardRequest createBoardRequest
    ){
        User user = authService.getUserByAccessToken(accessToken);

        return boardService.postBoard(user , createBoardRequest);
    }

    @ApiOperation("게시글 검색")
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
    @ApiOperation("게시글 상세 정보")
    @GetMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public BoardInfoResponse getBoard(@PathVariable long boardId){
        return boardService.getBoard(boardId);
    }

    @ApiOperation("인기 게시글 얻기")
    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public SearchBoardsResponse getPopularBoards(@RequestParam(required = false, defaultValue = "0")  int currentPage){
        return boardService.getPopularBoards(currentPage);
    }

    @ApiOperation("게시글 수정")
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

    @ApiOperation("게시글 삭제")
    @DeleteMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteBoardResponse deleteBoard(
            @RequestHeader String accessToken,
            @PathVariable Long boardId
    ){
        User user = authService.getUserByAccessToken(accessToken);

        return boardService.deleteBoard(user.getId(), boardId);
    }

    @ApiOperation("게시글 추천")
    @PostMapping("/{boardId}/recommendation")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardRecommendationResponse recommendBoard(
            @RequestHeader String accessToken,
            @PathVariable Long boardId
    ){
        User user = authService.getUserByAccessToken(accessToken);

        return boardService.recommendBoard(user.getId(), boardId);
    }

    @ApiOperation("게시글 추천 취소")
    @DeleteMapping("/{boardId}/recommendation")
    @ResponseStatus(HttpStatus.OK)
    public BoardRecommendationResponse unRecommendBoard(
            @RequestHeader String accessToken,
            @PathVariable Long boardId
    ){
        User user = authService.getUserByAccessToken(accessToken);

        return boardService.unRecommendBoard(user.getId(), boardId);
    }
}
