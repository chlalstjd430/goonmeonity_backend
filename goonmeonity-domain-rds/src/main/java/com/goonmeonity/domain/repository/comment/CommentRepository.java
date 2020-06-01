package com.goonmeonity.domain.repository.comment;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.repository.ExtendRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ExtendRepository<Comment> {
    Page<Comment> findAllByBoardIdOrderByCreatedDateDesc(long boardId, Pageable pageable);
}
