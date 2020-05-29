package com.goonmeonity.domain.service.board.dto;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.entity.board.BoardCategory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardInfo {
    private Long id;
    private String title;
    private String content;
    private BoardCategory boardCategory;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String author;

    public BoardInfo(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.boardCategory = board.getBoardCategory();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.author = board.getAuthor().getNickname();
    }
}
