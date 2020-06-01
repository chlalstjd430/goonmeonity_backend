package com.goonmeonity.domain.service.comment.function;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.repository.comment.CommentRepository;
import com.goonmeonity.domain.service.comment.dto.FindCommentsCondition;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.function.Function;

@AllArgsConstructor
public class FindCommentsFunction implements Function<FindCommentsCondition, Page<Comment>> {
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> apply(FindCommentsCondition findCommentsCondition) {
        return commentRepository.findAllByBoardIdOrderByCreatedDateDesc(
                findCommentsCondition.getBoardId(), PageRequest.of(findCommentsCondition.getCurrentPage(), 10)
        );
    }
}
