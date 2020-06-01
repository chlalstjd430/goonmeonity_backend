package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.repository.comment.CommentRepository;
import com.goonmeonity.domain.service.board.validator.CheckExistBoard;
import com.goonmeonity.domain.service.comment.dto.CommentInfo;
import com.goonmeonity.domain.service.comment.function.SaveComment;
import com.goonmeonity.external.api.response.comment.CreateCommentResponse;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    private final SaveComment saveComment;

    private final CheckExistBoard checkExistBoard;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;

        this.saveComment = new SaveComment(commentRepository);

        this.checkExistBoard = new CheckExistBoard(boardRepository);
    }

    public CreateCommentResponse createComment(User user, long boardId, String content){
        checkExistBoard.verify(boardId);

        Comment comment = Comment.builder()
                .author(user)
                .boardId(boardId)
                .content(content)
                .build();

        comment = saveComment.apply(comment);

        return new CreateCommentResponse(new CommentInfo(comment));
    }
}
