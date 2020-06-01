package com.goonmeonity.external.api.response.board;

import com.goonmeonity.domain.service.board.dto.BoardInfo;
import lombok.*;

@Getter
@AllArgsConstructor
public class BoardInfoResponse {
    private BoardInfo boardInfo;
}
