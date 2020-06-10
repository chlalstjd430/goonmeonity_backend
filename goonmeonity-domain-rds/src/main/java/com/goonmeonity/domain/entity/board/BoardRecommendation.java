package com.goonmeonity.domain.entity.board;

import com.goonmeonity.domain.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board_recommendations")
public class BoardRecommendation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long boardId;

    @Builder
    public BoardRecommendation(long userId, long boardId) {
        this.userId = userId;
        this.boardId = boardId;
    }
}
