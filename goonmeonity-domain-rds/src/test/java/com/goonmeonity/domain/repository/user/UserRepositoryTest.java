package com.goonmeonity.domain.repository.user;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.service.user.error.EmailIsAlreadyExistError;
import com.goonmeonity.domain.service.user.error.UserNotExistError;
import com.goonmeonity.domain.service.user.validator.CheckDuplicateUserEmail;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void 회원가입_성공(){
        //given
        User testUser = new User(null, "test2@naver.com","test","test");
        //when
        User resultUser = userRepository.save(testUser);
        //then
        Assertions.assertThat(resultUser.getId()).isNotNull();
    }

    @Test(expected = EmailIsAlreadyExistError.class)
    public void 이메일_중복검사_중복(){
        //given
        User user = signUp();
        //when
        CheckDuplicateUserEmail checkDuplicateUserEmail = new CheckDuplicateUserEmail(userRepository);
        //then
        checkDuplicateUserEmail.verify(user.getEmail());
    }

    @Test
    public void 에메일_중복검사_중복아님(){
        //given, when
        CheckDuplicateUserEmail checkDuplicateUserEmail = new CheckDuplicateUserEmail(userRepository);
        //then
        checkDuplicateUserEmail.verify("duplicate_test@gmail.com");
    }

    private User signUp(){
        //given
        User testUser = new User(null, "test@naver.com","test","test");
        //when
        return userRepository.save(testUser);
    }
}
