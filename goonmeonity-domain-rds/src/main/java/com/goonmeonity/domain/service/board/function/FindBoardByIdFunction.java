package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.service.board.error.BoardDoesNotExistError;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class FindBoardByIdFunction implements Function<Long, Board> {
    private BoardRepository boardRepository;

    @Override
    public Board apply(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardDoesNotExistError::new);
    }
}