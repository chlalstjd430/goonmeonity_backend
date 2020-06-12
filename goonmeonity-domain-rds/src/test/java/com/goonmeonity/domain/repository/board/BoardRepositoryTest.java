package com.goonmeonity.domain.repository.board;

import com.goonmeonity.domain.entity.board.Board;
import com.goonmeonity.domain.entity.board.BoardCategory;
import com.goonmeonity.domain.entity.board.BoardRecommendation;
import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaAuditing
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRecommendationRepository boardRecommendationRepository;

    @Test
    public void 회원가입_성공(){
        //given
        LocalDateTime localDate = LocalDateTime.now().minusDays(1);
        localDate.toString();

        //when
        Board board = createBoards();
        Page<Board> boards = boardRepository.findPopularBoards(localDate, PageRequest.of(0, 10));

        //then
        Assertions.assertThat(boards.getContent().size()).isEqualTo(1);
    }

    private Board createBoards(){
        User user = signUp();
        Board board = boardRepository.save(Board.builder()
                .boardCategory(BoardCategory.COUNSEL)
                .content("test title")
                .title("test title")
                .author(user)
                .build()
        );

        BoardRecommendation boardRecommendation = BoardRecommendation.builder()
                .userId(user.getId())
                .boardId(board.getId())
                .build();

        boardRecommendationRepository.save(boardRecommendation);

        return board;
    }

    private User signUp(){
        //given
        User testUser = new User(null, "test33@naver.com","test33","test33");
        //when
        return userRepository.save(testUser);
    }

}