package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.service.board.dto.BoardIdAndUserId;
import com.goonmeonity.domain.service.board.dto.BoardInfo;
import com.goonmeonity.domain.service.board.dto.SearchBoardInfo;
import com.goonmeonity.domain.service.board.function.DeleteBoardById;
import com.goonmeonity.domain.service.board.function.FindBoardByIdAndAuthorId;
import com.goonmeonity.domain.service.board.function.SaveBoard;
import com.goonmeonity.domain.service.board.function.SearchBoardsByCategory;
import com.goonmeonity.external.api.request.board.CreateBoardRequest;
import com.goonmeonity.external.api.request.board.SearchBoardsRequest;
import com.goonmeonity.external.api.request.board.UpdateBoardRequest;
import com.goonmeonity.external.api.response.board.BoardInfoResponse;
import com.goonmeonity.external.api.response.board.DeleteBoardResponse;
import com.goonmeonity.external.api.response.board.SearchBoardsResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    private final SaveBoard saveBoard;
    private final SearchBoardsByCategory searchBoardsByCategory;
    private final FindBoardByIdAndAuthorId findBoardByIdAndAuthorId;
    private final DeleteBoardById deleteBoardById;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;

        this.saveBoard = new SaveBoard(boardRepository);
        this.searchBoardsByCategory = new SearchBoardsByCategory(boardRepository);
        this.findBoardByIdAndAuthorId = new FindBoardByIdAndAuthorId(boardRepository);
        this.deleteBoardById = new DeleteBoardById(boardRepository);
    }

    public BoardInfoResponse postBoard(User user, CreateBoardRequest createBoardRequest){
        Board newBoard = Board.builder()
                .boardCategory(createBoardRequest.getBoardCategory())
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .author(user)
                .build();

        Board resultBoard = saveBoard.apply(newBoard);

        return new BoardInfoResponse(new BoardInfo(resultBoard));
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

    public BoardInfoResponse updateBoard(Long boardId, Long userId, UpdateBoardRequest updateBoardRequest){
        Board board = findBoardByIdAndAuthorId.apply(new BoardIdAndUserId(boardId,userId));
        board.setTitle(updateBoardRequest.getTitle());
        board.setContent(updateBoardRequest.getContent());
        Board updatedBoard = saveBoard.apply(board);

        return new BoardInfoResponse(new BoardInfo(updatedBoard));
    }

    public DeleteBoardResponse deleteBoard(long userId, long boardId){
        Board board = findBoardByIdAndAuthorId.apply(new BoardIdAndUserId(boardId, userId));
        deleteBoardById.accept(boardId);

        return new DeleteBoardResponse("delete complete");
    }

}
