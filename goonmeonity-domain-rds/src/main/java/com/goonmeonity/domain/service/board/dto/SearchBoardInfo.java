package com.goonmeonity.domain.service.board.dto;

import com.goonmeonity.domain.entity.board.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchBoardInfo {
    private BoardCategory boardCategory;
    private String keyword;
    private int page;
}
