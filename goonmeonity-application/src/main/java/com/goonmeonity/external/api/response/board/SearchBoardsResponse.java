package com.goonmeonity.external.api.response.board;

import com.goonmeonity.domain.service.board.dto.BoardInfo;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchBoardsResponse {
    private List<BoardInfo> boardsInfo;
    private int totalPage;
    private int currentPage;
    private long boardsCount;

    @Builder
    public SearchBoardsResponse(List<BoardInfo> boardsInfo, int totalPage, int currentPage, long boardsCount) {
        this.boardsInfo = boardsInfo;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.boardsCount = boardsCount;
    }
}
