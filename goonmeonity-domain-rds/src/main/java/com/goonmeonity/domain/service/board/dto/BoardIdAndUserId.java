package com.goonmeonity.domain.service.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardIdAndUserId {
    private long boardId;
    private long userId;
}
