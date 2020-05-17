package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.user.dto.UserInfo;
import com.goonmeonity.domain.service.user.error.EmailIsAlreadyExistError;
import com.goonmeonity.domain.service.user.function.SignUpUser;
import com.goonmeonity.domain.service.user.validator.CheckDuplicateUserEmail;
import com.goonmeonity.domain.service.user.validator.CheckDuplicateUserNickname;
import com.goonmeonity.external.api.request.SignUpRequest;
import com.goonmeonity.external.api.response.CheckDuplicateResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AuthService {
    private UserRepository userRepository;

    private SignUpUser signUpUser;

    private CheckDuplicateUserEmail checkDuplicateUserEmail;
    private CheckDuplicateUserNickname checkDuplicateUserNickname ;

    @Autowired
    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.signUpUser = new SignUpUser(userRepository);
        this.checkDuplicateUserNickname = new CheckDuplicateUserNickname(userRepository);
        this.checkDuplicateUserEmail = new CheckDuplicateUserEmail(userRepository);
    }

    public UserInfo signUpByEmail(SignUpRequest signUpRequest){
        checkDuplicateUserEmail.verify(signUpRequest.getEmail());
        checkDuplicateUserNickname.verify(signUpRequest.getNickname());

        User user = signUpUser.apply(
                new User(null, signUpRequest.getEmail(), signUpRequest.getNickname(), signUpRequest.getHashedPassword()
                )
        );

        return new UserInfo(user);
    }

    public CheckDuplicateResponse checkDuplicateEmail(String email){
        try {
            checkDuplicateUserEmail.verify(email);
        }catch (EmailIsAlreadyExistError error){
            return new CheckDuplicateResponse(false, "중복된 이메일 입니다.");
        }

        return new CheckDuplicateResponse(true, "중복되지 않은 이메일 입니다.");
    }

    public CheckDuplicateResponse checkDuplicateNickname(String nickname){
        try {
            checkDuplicateUserNickname.verify(nickname);
        }catch (EmailIsAlreadyExistError error){
            return new CheckDuplicateResponse(false, "중복된 닉네임 입니다.");
        }

        return new CheckDuplicateResponse(true, "중복되지 않은 닉네임 입니다.");
    }
}
