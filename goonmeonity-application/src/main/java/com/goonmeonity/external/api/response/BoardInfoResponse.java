package com.goonmeonity.external.api.response;

import com.goonmeonity.domain.entity.board.BoardCategory;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardInfoResponse {
    private Long id;
    private String title;
    private String content;
    private BoardCategory boardCategory;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public BoardInfoResponse(Long id, String title, String content, BoardCategory boardCategory, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
