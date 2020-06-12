package com.goonmeonity.domain.repository.board;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.entity.board.BoardCategory;
import com.goonmeonity.domain.repository.ExtendRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BoardRepository extends ExtendRepository<Board> {
    @Query("SELECT b FROM Board b where b.boardCategory = :boardCategory " +
            "and (b.title LIKE %:title% OR b.content LIKE %:content%)")
    Page<Board> findAllByBoardCategoryAndTitleContainingOrContentContaining(
            BoardCategory boardCategory, String title, String content, Pageable pageable
    );

    @Query("SELECT b FROM Board b " +
            "LEFT JOIN com.goonmeonity.domain.entity.board.BoardRecommendation AS br " +
            "ON b.id = br.boardId " +
            "group by b.id " +
            "HAVING COUNT(br.boardId) >= 1 and b.createdDate >= :date " +
            "ORDER BY COUNT(br.boardId) DESC ")
    Page<Board> findPopularBoards(@Param("date") LocalDateTime nowDate, Pageable pageable);

    Page<Board> findAll(Pageable pageable);

    Optional<Board> findBoardByIdAndAuthorId(Long id, Long authorId);
}