package com.goonmeonity.domain.service.board.validator;

import com.goonmeonity.core.validator.ValidatorWithError;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.service.board.error.BoardDoesNotExistError;

public class CheckExistBoardValidator extends ValidatorWithError<Long> {
    private BoardRepository boardRepository;

    public CheckExistBoardValidator(BoardRepository boardRepository) {
        super(new BoardDoesNotExistError());
        this.boardRepository = boardRepository;
    }

    @Override
    public Boolean apply(Long boardId) {
        return boardRepository.existsById(boardId);
    }
}