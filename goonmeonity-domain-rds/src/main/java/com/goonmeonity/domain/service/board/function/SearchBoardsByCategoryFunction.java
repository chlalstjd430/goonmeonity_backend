package com.goonmeonity.domain.service.board.function;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.service.board.dto.SearchBoardInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.function.Function;

@AllArgsConstructor
public class SearchBoardsByCategoryFunction implements Function<SearchBoardInfo, Page<Board>> {
    private BoardRepository boardRepository;

    @Override
    public Page<Board> apply(SearchBoardInfo searchBoardInfo) {
        return boardRepository.findAllByBoardCategoryAndTitleContainingOrContentContaining(
                searchBoardInfo.getBoardCategory(),
                searchBoardInfo.getKeyword(),
                searchBoardInfo.getKeyword(),
                PageRequest.of(searchBoardInfo.getPage(), 10, Sort.by("createdDate").descending())
        );
    }
}