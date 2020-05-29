package com.goonmeonity.domain.entity.board;

import com.goonmeonity.domain.entity.BaseTimeEntity;
import com.goonmeonity.domain.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "boards")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardCategory boardCategory;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Builder
    public Board(BoardCategory boardCategory, String title, String content, User author) {
        this.boardCategory = boardCategory;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
