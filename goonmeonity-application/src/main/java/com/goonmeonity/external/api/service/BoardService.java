package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.service.board.dto.BoardInfo;
import com.goonmeonity.domain.service.board.dto.SearchBoardInfo;
import com.goonmeonity.domain.service.board.function.CreateBoard;
import com.goonmeonity.domain.service.board.function.SearchBoardsByCategory;
import com.goonmeonity.external.api.request.CreateBoardRequest;
import com.goonmeonity.external.api.request.SearchBoardsRequest;
import com.goonmeonity.external.api.response.BoardInfoResponse;
import com.goonmeonity.external.api.response.SearchBoardsResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    private final CreateBoard createBoard;
    private final SearchBoardsByCategory searchBoardsByCategory;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;

        this.createBoard = new CreateBoard(boardRepository);
        this.searchBoardsByCategory = new SearchBoardsByCategory(boardRepository);
    }

    public BoardInfoResponse postBoard(User user, CreateBoardRequest createBoardRequest){
        Board newBoard = Board.builder()
                .boardCategory(createBoardRequest.getBoardCategory())
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .author(user)
                .build();

        Board resultBoard = createBoard.apply(newBoard);

        BoardInfo boardInfo = new BoardInfo(resultBoard);

        return BoardInfoResponse.builder()
                .boardInfo(boardInfo)
                .build();
    }

    public SearchBoardsResponse searchBoards(SearchBoardsRequest searchBoardsRequest){
        Page<Board> boards = searchBoardsByCategory.apply(
                new SearchBoardInfo(
                        searchBoardsRequest.getBoardCategory(),
                        searchBoardsRequest.getKeyword(),
                        searchBoardsRequest.getCurrentPage()
                )
        );
        List<BoardInfo> boardsInfo = new ArrayList<>();
        boards.getContent().forEach(board -> boardsInfo.add(new BoardInfo(board)));

        return SearchBoardsResponse.builder()
                .boardsInfo(boardsInfo)
                .totalPage(boards.getTotalPages())
                .currentPage(boards.getNumber())
                .boardsCount(boards.getTotalElements())
                .build();
    }

}
