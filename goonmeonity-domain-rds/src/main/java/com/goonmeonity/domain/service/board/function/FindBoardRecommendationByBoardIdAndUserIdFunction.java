package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.entity.board.BoardRecommendation;
import com.goonmeonity.domain.repository.board.BoardRecommendationRepository;
import com.goonmeonity.domain.service.board.dto.BoardIdAndUserId;
import com.goonmeonity.domain.service.board.error.BoardRecommendationDoesNotExistError;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class FindBoardRecommendationByBoardIdAndUserIdFunction implements Function<BoardIdAndUserId, BoardRecommendation> {
    private BoardRecommendationRepository boardRecommendationRepository;

    @Override
    public BoardRecommendation apply(BoardIdAndUserId boardIdAndUserId) {
        return boardRecommendationRepository.findByBoardIdAndUserId(boardIdAndUserId.getBoardId(), boardIdAndUserId.getUserId())
                .orElseThrow(BoardRecommendationDoesNotExistError::new);
    }
}