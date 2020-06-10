package com.goonmeonity.domain.service.board.validator;

import com.goonmeonity.core.validator.ValidatorWithError;
import com.goonmeonity.domain.repository.board.BoardRecommendationRepository;
import com.goonmeonity.domain.service.board.dto.BoardIdAndUserId;
import com.goonmeonity.domain.service.board.error.BoardRecommendationAlreadyExistError;

public class CheckBoardRecommendationDoesNotExistValidator extends ValidatorWithError<BoardIdAndUserId> {
    private BoardRecommendationRepository boardRecommendationRepository;

    public CheckBoardRecommendationDoesNotExistValidator(BoardRecommendationRepository boardRecommendationRepository) {
        super(new BoardRecommendationAlreadyExistError());
        this.boardRecommendationRepository = boardRecommendationRepository;
    }

    @Override
    public Boolean apply(BoardIdAndUserId boardIdAndUserId) {
        return !boardRecommendationRepository.existsByBoardIdAndUserId(
                boardIdAndUserId.getBoardId(),
                boardIdAndUserId.getUserId()
        );
    }
}