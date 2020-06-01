package com.goonmeonity.domain.entity.comment;

import com.goonmeonity.domain.entity.BaseTimeEntity;
import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorId", nullable = false)
    private User author;

    @Column(nullable = false)
    private long boardId;

    @Builder
    public Comment(String content, User author, long boardId) {
        this.content = content;
        this.author = author;
        this.boardId = boardId;
    }
}
