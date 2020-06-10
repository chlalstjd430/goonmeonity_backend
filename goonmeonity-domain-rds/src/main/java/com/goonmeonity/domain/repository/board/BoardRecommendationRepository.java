package com.goonmeonity.domain.repository.board;

import com.goonmeonity.domain.entity.board.BoardRecommendation;
import com.goonmeonity.domain.repository.ExtendRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRecommendationRepository extends ExtendRepository<BoardRecommendation> {
    boolean existsByBoardIdAndUserId(long boardId, long userId);

    List<BoardRecommendation> findAllByBoardId(long boardId);

    Optional<BoardRecommendation> findByBoardIdAndUserId(long boardId, long userId);
}
