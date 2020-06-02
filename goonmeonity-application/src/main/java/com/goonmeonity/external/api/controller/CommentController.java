package com.goonmeonity.external.api.controller;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.external.api.response.comment.CreateCommentResponse;
import com.goonmeonity.external.api.response.comment.DeleteCommentResponse;
import com.goonmeonity.external.api.response.comment.GetCommentsResponse;
import com.goonmeonity.external.api.response.comment.UpdateCommentResponse;
import com.goonmeonity.external.api.service.AuthService;
import com.goonmeonity.external.api.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/boards/{boardId}/comments")
public class CommentController {
    private CommentService commentService;
    private AuthService authService;

    public CommentController(CommentService commentService, AuthService authService) {
        this.commentService = commentService;
        this.authService = authService;
    }

    @ApiOperation("댓글 작성")
    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCommentResponse createComment(
            @RequestHeader String accessToken,
            @PathVariable long boardId,
            @RequestBody String content
    ){
        User user = authService.getUserByAccessToken(accessToken);

        return commentService.createComment(user, boardId, content);
    }

    @ApiOperation("댓글 정보 얻기")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetCommentsResponse getComments(@PathVariable long boardId){
        return commentService.getComments(boardId);
    }

    @ApiOperation("댓글 수정")
    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateCommentResponse updateComment(
            @RequestHeader String accessToken,
            @PathVariable long boardId,
            @PathVariable long commentId,
            @RequestBody String content
    ){
        User user = authService.getUserByAccessToken(accessToken);

        return commentService.updateComment(boardId, commentId, user.getId(), content);
    }

    @ApiOperation("댓글 삭제")
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteCommentResponse deleteComment(
            @RequestHeader String accessToken,
            @PathVariable long boardId,
            @PathVariable long commentId
    ){
        User user = authService.getUserByAccessToken(accessToken);

        return commentService.deleteComment(boardId, commentId, user.getId());
    }
}