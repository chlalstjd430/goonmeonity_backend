package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.service.board.function.CreateBoard;
import com.goonmeonity.external.api.request.CreateBoardRequest;
import com.goonmeonity.external.api.response.BoardInfoResponse;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    private final CreateBoard createBoard;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;

        this.createBoard = new CreateBoard(boardRepository);
    }

    public BoardInfoResponse create(User user, CreateBoardRequest createBoardRequest){
        Board newBoard = Board.builder()
                .boardCategory(createBoardRequest.getBoardCategory())
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .author(user)
                .build();

        Board resultBoard = createBoard.apply(newBoard);

        return BoardInfoResponse.builder()
                .id(resultBoard.getId())
                .boardCategory(resultBoard.getBoardCategory())
                .content(resultBoard.getContent())
                .title(resultBoard.getTitle())
                .createdDate(resultBoard.getCreatedDate())
                .modifiedDate(resultBoard.getModifiedDate())
                .build();
    }
}
