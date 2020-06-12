package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.repository.board.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.function.Function;

@AllArgsConstructor
public class FindPopularBoardsFunction implements Function<Integer, Page<Board>> {
    private BoardRepository boardRepository;

    @Override
    public Page<Board> apply(Integer page) {
        return boardRepository.findPopularBoards(
                LocalDateTime.now().minusDays(1),
                PageRequest.of(page, 10)
        );
    }
}
