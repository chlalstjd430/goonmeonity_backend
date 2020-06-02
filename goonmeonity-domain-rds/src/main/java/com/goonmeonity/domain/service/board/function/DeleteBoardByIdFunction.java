package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.repository.board.BoardRepository;
import lombok.AllArgsConstructor;

import java.util.function.Consumer;

@AllArgsConstructor
public class DeleteBoardByIdFunction implements Consumer<Long> {
    private BoardRepository boardRepository;

    @Override
    public void accept(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
