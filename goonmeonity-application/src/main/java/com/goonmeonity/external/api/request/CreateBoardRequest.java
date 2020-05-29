package com.goonmeonity.external.api.request;

import com.goonmeonity.domain.entity.board.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBoardRequest {
    private String title;
    private String content;
    private BoardCategory boardCategory;
}
