package com.goonmeonity.domain.service.comment.function;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.repository.comment.CommentRepository;
import com.goonmeonity.domain.service.comment.dto.CommentIdAndUserIdAndBoardId;
import com.goonmeonity.domain.service.comment.error.UserDoesNotHaveCommentPermissionError;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class FindCommentByIdAndUserIdAndBoardIdFunction implements Function<CommentIdAndUserIdAndBoardId, Comment> {
    private CommentRepository commentRepository;

    @Override
    public Comment apply(CommentIdAndUserIdAndBoardId commentIdAndUserIdAndBoardId) {
        return commentRepository.findCommentByIdAndAuthorIdAndBoardId(
                commentIdAndUserIdAndBoardId.getCommentId(),
                commentIdAndUserIdAndBoardId.getUserId(),
                commentIdAndUserIdAndBoardId.getBoardId()
        )
                .orElseThrow(UserDoesNotHaveCommentPermissionError::new);
    }
}
