package com.goonmeonity.domain.service.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindCommentsCondition {
    private long boardId;
    private int currentPage;
}
