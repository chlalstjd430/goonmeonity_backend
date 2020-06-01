package com.goonmeonity.domain.service.comment.function;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.repository.comment.CommentRepository;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class SaveComment implements Function<Comment, Comment> {
    private CommentRepository commentRepository;

    @Override
    public Comment apply(Comment comment) {
        return commentRepository.save(comment);
    }
}
