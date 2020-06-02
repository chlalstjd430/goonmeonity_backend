package com.goonmeonity.domain.service.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentIdAndUserIdAndBoardId {
    private long commentId;
    private long userId;
    private long boardId;
}
