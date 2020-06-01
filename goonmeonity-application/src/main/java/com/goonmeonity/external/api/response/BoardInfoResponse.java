package com.goonmeonity.external.api.response;

import com.goonmeonity.domain.service.board.dto.BoardInfo;
import lombok.*;

@Getter
@AllArgsConstructor
public class BoardInfoResponse {
    private BoardInfo boardInfo;
}
