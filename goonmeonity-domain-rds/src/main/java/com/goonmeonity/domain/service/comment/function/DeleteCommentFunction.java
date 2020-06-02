package com.goonmeonity.domain.service.comment.function;

import com.goonmeonity.domain.repository.comment.CommentRepository;
import lombok.AllArgsConstructor;

import java.util.function.Consumer;

@AllArgsConstructor
public class DeleteCommentFunction implements Consumer<Long> {
    private CommentRepository commentRepository;

    @Override
    public void accept(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
