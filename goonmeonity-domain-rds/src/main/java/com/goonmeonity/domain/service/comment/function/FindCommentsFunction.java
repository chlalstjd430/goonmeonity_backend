package com.goonmeonity.domain.service.comment.function;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.repository.comment.CommentRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class FindCommentsFunction implements Function<Long, List<Comment>> {
    private CommentRepository commentRepository;

    @Override
    public List<Comment> apply(Long boardId) {
        return commentRepository.findAllByBoardIdOrderByCreatedDate(boardId);
    }
}
