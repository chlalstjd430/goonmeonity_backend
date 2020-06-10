package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.entity.board.BoardRecommendation;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.board.BoardRecommendationRepository;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.service.board.dto.BoardIdAndUserId;
import com.goonmeonity.domain.service.board.dto.BoardInfo;
import com.goonmeonity.domain.service.board.dto.SearchBoardInfo;
import com.goonmeonity.domain.service.board.function.*;
import com.goonmeonity.domain.service.board.validator.CheckBoardRecommendationDoesNotExistValidator;
import com.goonmeonity.domain.service.board.validator.CheckExistBoardValidator;
import com.goonmeonity.external.api.request.board.CreateBoardRequest;
import com.goonmeonity.external.api.request.board.SearchBoardsRequest;
import com.goonmeonity.external.api.request.board.UpdateBoardRequest;
import com.goonmeonity.external.api.response.board.BoardInfoResponse;
import com.goonmeonity.external.api.response.board.BoardRecommendationResponse;
import com.goonmeonity.external.api.response.board.DeleteBoardResponse;
import com.goonmeonity.external.api.response.board.SearchBoardsResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardRecommendationRepository boardRecommendationRepository;

    private final SaveBoardFunction saveBoardFunction;
    private final SearchBoardsByCategoryFunction searchBoardsByCategoryFunction;
    private final FindBoardByIdAndAuthorIdFunction findBoardByIdAndAuthorIdFunction;
    private final DeleteBoardByIdFunction deleteBoardByIdFunction;
    private final SaveBoardRecommendationFunction saveBoardRecommendationFunction;
    private final FindBoardRecommendationByBoardIdFunction findBoardRecommendationByBoardIdFunction;
    private final FindBoardRecommendationByBoardIdAndUserIdFunction findBoardRecommendationByBoardIdAndUserIdFunction;
    private final DeleteBoardRecommendationByIdFunction deleteBoardRecommendationByIdFunction;

    private final CheckBoardRecommendationDoesNotExistValidator checkBoardRecommendationDoesNotExistValidator;

    private final CheckExistBoardValidator checkExistBoardValidator;
    public BoardService(BoardRepository boardRepository, BoardRecommendationRepository boardRecommendationRepository) {
        this.boardRepository = boardRepository;
        this.boardRecommendationRepository = boardRecommendationRepository;

        this.saveBoardFunction = new SaveBoardFunction(boardRepository);
        this.searchBoardsByCategoryFunction = new SearchBoardsByCategoryFunction(boardRepository);
        this.findBoardByIdAndAuthorIdFunction = new FindBoardByIdAndAuthorIdFunction(boardRepository);
        this.deleteBoardByIdFunction = new DeleteBoardByIdFunction(boardRepository);
        this.saveBoardRecommendationFunction = new SaveBoardRecommendationFunction(boardRecommendationRepository);
        this.checkExistBoardValidator = new CheckExistBoardValidator(boardRepository);
        this.findBoardRecommendationByBoardIdFunction
                = new FindBoardRecommendationByBoardIdFunction(boardRecommendationRepository);
        this.findBoardRecommendationByBoardIdAndUserIdFunction
                = new FindBoardRecommendationByBoardIdAndUserIdFunction(boardRecommendationRepository);
        this.deleteBoardRecommendationByIdFunction = new DeleteBoardRecommendationByIdFunction(boardRecommendationRepository);

        this.checkBoardRecommendationDoesNotExistValidator
                = new CheckBoardRecommendationDoesNotExistValidator(boardRecommendationRepository);
    }

    public BoardInfoResponse postBoard(User user, CreateBoardRequest createBoardRequest){
        Board newBoard = Board.builder()
                .boardCategory(createBoardRequest.getBoardCategory())
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .author(user)
                .build();

        Board resultBoard = saveBoardFunction.apply(newBoard);

        return new BoardInfoResponse(new BoardInfo(resultBoard, 0));
    }

    public SearchBoardsResponse searchBoards(SearchBoardsRequest searchBoardsRequest){
        Page<Board> boards = searchBoardsByCategoryFunction.apply(
                new SearchBoardInfo(
                        searchBoardsRequest.getBoardCategory(),
                        searchBoardsRequest.getKeyword(),
                        searchBoardsRequest.getCurrentPage()
                )
        );
        List<BoardInfo> boardsInfo = new ArrayList<>();

        boards.getContent().forEach(board ->
                boardsInfo.add(
                        new BoardInfo(
                                board,
                                findBoardRecommendationByBoardIdFunction.apply(board.getId()).size()
                        )
                )
        );

        return SearchBoardsResponse.builder()
                .boardsInfo(boardsInfo)
                .totalPage(boards.getTotalPages())
                .currentPage(boards.getNumber())
                .boardsCount(boards.getTotalElements())
                .build();
    }

    public BoardInfoResponse updateBoard(Long boardId, Long userId, UpdateBoardRequest updateBoardRequest){
        Board board = findBoardByIdAndAuthorIdFunction.apply(new BoardIdAndUserId(boardId,userId));
        board.setTitle(updateBoardRequest.getTitle());
        board.setContent(updateBoardRequest.getContent());
        Board updatedBoard = saveBoardFunction.apply(board);
        int recommendCount = findBoardRecommendationByBoardIdFunction.apply(updatedBoard.getId()).size();
        return new BoardInfoResponse(new BoardInfo(updatedBoard, recommendCount));
    }

    public DeleteBoardResponse deleteBoard(long userId, long boardId){
        Board board = findBoardByIdAndAuthorIdFunction.apply(new BoardIdAndUserId(boardId, userId));
        deleteBoardByIdFunction.accept(boardId);

        return new DeleteBoardResponse("delete complete");
    }

    public BoardRecommendationResponse recommendBoard(long userId, long boardId){
        checkExistBoardValidator.verify(boardId);
        checkBoardRecommendationDoesNotExistValidator.verify(new BoardIdAndUserId(boardId, userId));
        BoardRecommendation boardRecommendation = saveBoardRecommendationFunction.apply(
                BoardRecommendation.builder()
                .boardId(boardId)
                .userId(userId)
                .build()
        );

        return new BoardRecommendationResponse(true);
    }

    public BoardRecommendationResponse unRecommendBoard(long userId, long boardId){
        checkExistBoardValidator.verify(boardId);
        BoardRecommendation boardRecommendation =findBoardRecommendationByBoardIdAndUserIdFunction.apply(
                new BoardIdAndUserId(boardId, userId)
        );
        deleteBoardRecommendationByIdFunction.accept(boardRecommendation.getId());

        return new BoardRecommendationResponse(true);
    }
}
