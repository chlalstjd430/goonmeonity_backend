package com.goonmeonity.external.api.response.comment;

import com.goonmeonity.domain.service.comment.dto.CommentInfo;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentResponse {
    private CommentInfo commentInfo;
}
