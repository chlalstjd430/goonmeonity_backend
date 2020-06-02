package com.goonmeonity.domain.repository.comment;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.repository.ExtendRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends ExtendRepository<Comment> {
    Page<Comment> findAllByBoardIdOrderByCreatedDateDesc(long boardId, Pageable pageable);

    Optional<Comment> findCommentByIdAndAuthorIdAndBoardId(long commentId, long authorId, long boardId);
}
