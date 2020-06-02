package com.goonmeonity.external.api.response.comment;

import com.goonmeonity.domain.service.comment.dto.CommentInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetCommentsResponse {
    private List<CommentInfo> commentsInfo;
    private long commentsCount;

    @Builder
    public GetCommentsResponse(List<CommentInfo> commentsInfo, long commentsCount) {
        this.commentsInfo = commentsInfo;
        this.commentsCount = commentsCount;
    }
}
