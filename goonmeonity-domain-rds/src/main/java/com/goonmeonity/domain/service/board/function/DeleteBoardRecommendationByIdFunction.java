package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.repository.board.BoardRecommendationRepository;
import lombok.AllArgsConstructor;

import java.util.function.Consumer;

@AllArgsConstructor
public class DeleteBoardRecommendationByIdFunction implements Consumer<Long> {
    private BoardRecommendationRepository boardRecommendationRepository;

    @Override
    public void accept(Long id) {
        boardRecommendationRepository.deleteById(id);
    }
}
