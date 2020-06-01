package com.goonmeonity.domain.repository.board;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.entity.board.BoardCategory;
import com.goonmeonity.domain.repository.ExtendRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends ExtendRepository<Board> {
    @Query("SELECT b FROM Board b where b.boardCategory = :boardCategory " +
            "and (b.title LIKE %:title% OR b.content LIKE %:content%)")
    Page<Board> findAllByBoardCategoryAndTitleContainingOrContentContaining(
            BoardCategory boardCategory, String title, String content, Pageable pageable
    );

    Page<Board> findAll(Pageable pageable);

    Optional<Board> findBoardByIdAndAuthorId(Long id, Long authorId);
}
