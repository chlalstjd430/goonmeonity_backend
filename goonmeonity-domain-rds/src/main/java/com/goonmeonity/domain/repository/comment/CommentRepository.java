package com.goonmeonity.domain.repository.comment;

import com.goonmeonity.domain.entity.comment.Comment;
import com.goonmeonity.domain.repository.ExtendRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ExtendRepository<Comment> {
}
