package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.board.BoardRepository;
import com.goonmeonity.domain.repository.comment.CommentRepository;
import com.goonmeonity.domain.service.board.validator.CheckExistBoardValidator;
import com.goonmeonity.domain.service.comment.dto.CommentIdAndUserIdAndBoardId;
import com.goonmeonity.domain.service.comment.dto.CommentInfo;
import com.goonmeonity.domain.service.comment.error.UserDoesNotHaveCommentPermissionError;
import com.goonmeonity.domain.service.comment.function.DeleteCommentFunction;
import com.goonmeonity.domain.service.comment.function.FindCommentByIdAndUserIdAndBoardIdFunction;
import com.goonmeonity.domain.service.comment.function.FindCommentsFunction;
import com.goonmeonity.domain.service.comment.function.SaveCommentFunction;
import com.goonmeonity.external.api.response.comment.CreateCommentResponse;
import com.goonmeonity.external.api.response.comment.DeleteCommentResponse;
import com.goonmeonity.external.api.response.comment.GetCommentsResponse;
import com.goonmeonity.external.api.response.comment.UpdateCommentResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    private final SaveCommentFunction saveCommentFunction;
    private final FindCommentsFunction findCommentsFunction;
    private final FindCommentByIdAndUserIdAndBoardIdFunction findCommentByIdAndUserIdAndBoardIdFunction;
    private final DeleteCommentFunction deleteCommentFunction;

    private final CheckExistBoardValidator checkExistBoardValidator;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;

        this.saveCommentFunction = new SaveCommentFunction(commentRepository);
        this.findCommentsFunction = new FindCommentsFunction(commentRepository);
        this.findCommentByIdAndUserIdAndBoardIdFunction = new FindCommentByIdAndUserIdAndBoardIdFunction(commentRepository);
        this.deleteCommentFunction = new DeleteCommentFunction(commentRepository);

        this.checkExistBoardValidator = new CheckExistBoardValidator(boardRepository);
    }

    public CreateCommentResponse createComment(User user, long boardId, String content){
        checkExistBoardValidator.verify(boardId);

        Comment comment = Comment.builder()
                .author(user)
                .boardId(boardId)
                .content(content)
                .build();

        comment = saveCommentFunction.apply(comment);

        return new CreateCommentResponse(new CommentInfo(comment));
    }

    public GetCommentsResponse getComments(long boardId){
        checkExistBoardValidator.verify(boardId);
        List<Comment> comments = findCommentsFunction.apply(boardId);

        return GetCommentsResponse.builder()
                .commentsInfo(commentsToCommentsInfo(comments))
                .commentsCount(comments.size())
                .build();
    }

    private List<CommentInfo> commentsToCommentsInfo(List<Comment> comments){
        List<CommentInfo> commentsInfo = new ArrayList<>();
        comments.forEach(comment -> commentsInfo.add(new CommentInfo(comment)));

        return commentsInfo;
    }

    public UpdateCommentResponse updateComment(long boardId, long commentId, long userId, String content){
        Comment beforeComment = findCommentByIdAndUserIdAndBoardIdFunction.apply(new CommentIdAndUserIdAndBoardId(commentId, userId, boardId));
        beforeComment.setContent(content);
        Comment updatedComment = saveCommentFunction.apply(beforeComment);

        boolean result = false;
        if(updatedComment.getContent().equals(content))
            result = true;

        return new UpdateCommentResponse(result);
    }

    public DeleteCommentResponse deleteComment(long boardId, long commentId, long userId){
        Comment comment = findCommentByIdAndUserIdAndBoardIdFunction.apply(new CommentIdAndUserIdAndBoardId(commentId, userId, boardId));

        deleteCommentFunction.accept(commentId);

        boolean result = false;
        try {
            comment = findCommentByIdAndUserIdAndBoardIdFunction.apply(new CommentIdAndUserIdAndBoardId(commentId, userId, boardId));
        }catch (UserDoesNotHaveCommentPermissionError error){
            result = true;
        }

        return new DeleteCommentResponse(result);
    }
}
