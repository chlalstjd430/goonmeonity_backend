package com.goonmeonity.external.api.response;

import com.goonmeonity.domain.entity.board.BoardCategory;
import com.goonmeonity.domain.service.board.dto.BoardInfo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardInfoResponse {
    private BoardInfo boardInfo;

    @Builder
    public BoardInfoResponse(BoardInfo boardInfo) {
        this.boardInfo = boardInfo;
    }
}
