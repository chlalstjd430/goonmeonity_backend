package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.repository.comment.CommentRepository;
import com.goonmeonity.domain.service.board.validator.CheckExistBoard;
import com.goonmeonity.domain.service.comment.dto.CommentInfo;
import com.goonmeonity.domain.service.comment.dto.FindCommentsCondition;
import com.goonmeonity.domain.service.comment.function.FindCommentsFunction;
import com.goonmeonity.domain.service.comment.function.SaveCommentFunction;
import com.goonmeonity.external.api.response.comment.CreateCommentResponse;
import com.goonmeonity.external.api.response.comment.GetCommentsResponse;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    private final SaveCommentFunction saveCommentFunction;
    private final FindCommentsFunction findCommentsFunction;

    private final CheckExistBoard checkExistBoard;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;

        this.saveCommentFunction = new SaveCommentFunction(commentRepository);
        this.findCommentsFunction = new FindCommentsFunction(commentRepository);

        this.checkExistBoard = new CheckExistBoard(boardRepository);
    }

    public CreateCommentResponse createComment(User user, long boardId, String content){
        checkExistBoard.verify(boardId);

        Comment comment = Comment.builder()
                .author(user)
                .boardId(boardId)
                .content(content)
                .build();

        comment = saveCommentFunction.apply(comment);

        return new CreateCommentResponse(new CommentInfo(comment));
    }

    public GetCommentsResponse getComments(long boardId, int currentPage){
        checkExistBoard.verify(boardId);
        Page<Comment> comments = findCommentsFunction.apply(new FindCommentsCondition(boardId, currentPage));

        return GetCommentsResponse.builder()
                .commentsInfo(commentsToCommentsInfo(comments.getContent()))
                .currentPage(comments.getNumber())
                .totalPage(comments.getTotalPages())
                .commentsCount(comments.getTotalElements())
                .build();
    }

    private List<CommentInfo> commentsToCommentsInfo(List<Comment> comments){
        List<CommentInfo> commentsInfo = new ArrayList<>();
        comments.forEach(comment -> commentsInfo.add(new CommentInfo(comment)));

        return commentsInfo;
    }
}
