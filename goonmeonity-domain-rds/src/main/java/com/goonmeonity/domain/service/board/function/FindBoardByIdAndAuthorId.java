package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.service.board.dto.BoardIdAndUserId;
import com.goonmeonity.domain.service.board.error.BoardDoesNotHavePermissionError;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class FindBoardByIdAndAuthorId implements Function<BoardIdAndUserId, Board> {
    private BoardRepository boardRepository;

    @Override
    public Board apply(BoardIdAndUserId boardIdAndUserId) {
        return boardRepository.findBoardByIdAndAuthorId(boardIdAndUserId.getBoardId(), boardIdAndUserId.getUserId())
                .orElseThrow(BoardDoesNotHavePermissionError::new);
    }
}