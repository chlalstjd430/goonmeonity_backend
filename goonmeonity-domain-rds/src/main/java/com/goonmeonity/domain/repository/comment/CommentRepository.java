package com.goonmeonity.domain.repository.comment;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.repository.ExtendRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends ExtendRepository<Comment> {
    List<Comment> findAllByBoardIdOrderByCreatedDate(long boardId);

    Optional<Comment> findCommentByIdAndAuthorIdAndBoardId(long commentId, long authorId, long boardId);
}
