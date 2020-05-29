package com.goonmeonity.domain.repository.board;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.repository.ExtendRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends ExtendRepository<Board> {
}
