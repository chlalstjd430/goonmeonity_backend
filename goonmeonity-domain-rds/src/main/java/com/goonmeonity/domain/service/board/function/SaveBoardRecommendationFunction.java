package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.entity.board.BoardRecommendation;
import com.goonmeonity.domain.repository.board.BoardRecommendationRepository;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class SaveBoardRecommendationFunction implements Function<BoardRecommendation, BoardRecommendation> {
    private BoardRecommendationRepository boardRecommendationRepository;

    @Override
    public BoardRecommendation apply(BoardRecommendation boardRecommendation) {
        return boardRecommendationRepository.save(boardRecommendation);
    }
}