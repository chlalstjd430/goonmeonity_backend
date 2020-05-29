package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.repository.board.BoardRepository;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class CreateBoard implements Function<Board, Board> {
    private BoardRepository boardRepository;

    @Override
    public Board apply(Board board) {
        return boardRepository.save(board);
    }
}