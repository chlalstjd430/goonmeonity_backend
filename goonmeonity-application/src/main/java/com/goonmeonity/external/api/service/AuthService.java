package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.auth.JwtTokenProvider;
import com.goonmeonity.domain.service.auth.dto.AccessToken;
import com.goonmeonity.domain.service.auth.dto.InputPasswordAndRealPassword;
import com.goonmeonity.domain.service.auth.validator.CheckPassword;
import com.goonmeonity.domain.service.user.dto.UserInfo;
import com.goonmeonity.domain.service.user.error.EmailIsAlreadyExistError;
import com.goonmeonity.domain.service.user.error.NicknameIsAlreadyExistError;
import com.goonmeonity.domain.service.user.function.FindUserByEmail;
import com.goonmeonity.domain.service.user.function.FindUserById;
import com.goonmeonity.domain.service.user.function.SignUpUser;
import com.goonmeonity.domain.service.user.validator.CheckDuplicateUserEmail;
import com.goonmeonity.domain.service.user.validator.CheckDuplicateUserNickname;
import com.goonmeonity.domain.service.user.validator.CheckUserExistById;
import com.goonmeonity.external.api.request.SignInRequest;
import com.goonmeonity.external.api.request.SignUpRequest;
import com.goonmeonity.external.api.response.CheckDuplicateResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("#{T(Integer).parseInt('${jwt.expiration}')}")
    private Integer expiration;
    private final UserRepository userRepository;

    private final SignUpUser signUpUser;
    private final FindUserById findUserById;
    private final FindUserByEmail findUserByEmail;

    private final CheckDuplicateUserEmail checkDuplicateUserEmail;
    private final CheckDuplicateUserNickname checkDuplicateUserNickname;
    private final CheckUserExistById checkUserExistById;
    private final CheckPassword checkPassword;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.signUpUser = new SignUpUser(userRepository);
        this.findUserById = new FindUserById(userRepository);
        this.checkDuplicateUserNickname = new CheckDuplicateUserNickname(userRepository);
        this.checkDuplicateUserEmail = new CheckDuplicateUserEmail(userRepository);
        this.checkUserExistById = new CheckUserExistById(userRepository);
        this.checkPassword = new CheckPassword();
        this.findUserByEmail = new FindUserByEmail(userRepository);
    }

    public SignInResponse signIn(SignInRequest signInRequest){
        User user = findUserByEmail.apply(signInRequest.getEmail());
        checkPassword.verify(
                new InputPasswordAndRealPassword(user.getHashedPassword(), signInRequest.getHashedPassword())
        );

        return new SignInResponse(
                new UserInfo(user),
                JwtTokenProvider.getInstance().generateAccessKey(user, secretKey, expiration),
                JwtTokenProvider.getInstance().generateRefreshToken(user, secretKey)
        );
    }

    public SignInResponse signUpByEmail(SignUpRequest signUpRequest){
        checkDuplicateUserEmail.verify(signUpRequest.getEmail());
        checkDuplicateUserNickname.verify(signUpRequest.getNickname());
        User user = signUpUser.apply(
                new User(
                        null,
                        signUpRequest.getEmail(),
                        signUpRequest.getNickname(),
                        signUpRequest.getHashedPassword()
                )
        );

        return new SignInResponse(
                new UserInfo(user),
                JwtTokenProvider.getInstance().generateAccessKey(user, secretKey, expiration),
                JwtTokenProvider.getInstance().generateRefreshToken(user, secretKey)
        );
    }

    public AccessToken refreshAccessToken(String refreshToken){
        Claims claims = JwtTokenProvider.getInstance().decodingToken(refreshToken, secretKey);
        long userId = JwtTokenProvider.getInstance().getUserIdByClaims(claims, "RefreshToken");
        checkUserExistById.verify(userId);

        return JwtTokenProvider.getInstance().generateAccessKey(
                findUserById.apply(userId),
                secretKey,
                expiration
        );
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
        }catch (NicknameIsAlreadyExistError error){
            return new CheckDuplicateResponse(false, "중복된 닉네임 입니다.");
        }

        return new CheckDuplicateResponse(true, "중복되지 않은 닉네임 입니다.");
    }
}
