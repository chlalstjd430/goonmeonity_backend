package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.entity.board.BoardRecommendation;
import com.goonmeonity.domain.repository.board.BoardRecommendationRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class FindBoardRecommendationByBoardIdFunction implements Function<Long, List<BoardRecommendation>> {
    private BoardRecommendationRepository boardRecommendationRepository;

    @Override
    public List<BoardRecommendation> apply(Long boardId) {
        return boardRecommendationRepository.findAllByBoardId(boardId);
    }
}