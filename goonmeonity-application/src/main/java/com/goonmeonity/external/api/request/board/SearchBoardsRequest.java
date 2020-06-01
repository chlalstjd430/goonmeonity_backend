package com.goonmeonity.external.api.request.board;

import com.goonmeonity.domain.entity.board.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchBoardsRequest {
    private BoardCategory boardCategory;
    private String keyword;
    private int currentPage;
}
