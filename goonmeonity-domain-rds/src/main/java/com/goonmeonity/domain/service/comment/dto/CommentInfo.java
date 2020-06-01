package com.goonmeonity.domain.service.comment.dto;

import com.goonmeonity.domain.entity.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentInfo {
    private long id;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentInfo(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getAuthor().getNickname();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}
