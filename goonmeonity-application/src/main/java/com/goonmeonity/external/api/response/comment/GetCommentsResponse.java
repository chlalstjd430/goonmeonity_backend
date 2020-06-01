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
    private int totalPage;
    private int currentPage;
    private long commentsCount;

    @Builder
    public GetCommentsResponse(List<CommentInfo> commentsInfo, int totalPage, int currentPage, long commentsCount) {
        this.commentsInfo = commentsInfo;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.commentsCount = commentsCount;
    }
}
