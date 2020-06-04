package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.auth.JwtTokenProvider;
import com.goonmeonity.domain.service.auth.dto.AccessToken;
import com.goonmeonity.domain.service.auth.dto.InputPasswordAndRealPassword;
import com.goonmeonity.domain.service.auth.validator.CheckPasswordValidator;
import com.goonmeonity.domain.service.user.dto.UserInfo;
import com.goonmeonity.domain.service.user.error.EmailIsAlreadyExistError;
import com.goonmeonity.domain.service.user.error.NicknameIsAlreadyExistError;
import com.goonmeonity.domain.service.user.function.FindUserByEmailFunction;
import com.goonmeonity.domain.service.user.function.FindUserByIdFunction;
import com.goonmeonity.domain.service.user.function.SignUpUserFunction;
import com.goonmeonity.domain.service.user.validator.CheckDuplicateUserEmailValidator;
import com.goonmeonity.domain.service.user.validator.CheckDuplicateUserNicknameValidator;
import com.goonmeonity.domain.service.user.validator.CheckUserExistByIdValidator;
import com.goonmeonity.external.api.request.auth.SignInRequest;
import com.goonmeonity.external.api.request.auth.SignUpRequest;
import com.goonmeonity.external.api.response.CheckDuplicateResponse;
import com.goonmeonity.external.api.response.auth.SignInResponse;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private String secretKey = "285EBCA0632782753C8803BA16DA1882960FE4D6C1043CE64B167AEF09C1CCE0";
    private Integer expiration = 600000;
    private final UserRepository userRepository;

    private final SignUpUserFunction signUpUserFunction;
    private final FindUserByIdFunction findUserByIdFunction;
    private final FindUserByEmailFunction findUserByEmailFunction;

    private final CheckDuplicateUserEmailValidator checkDuplicateUserEmailValidator;
    private final CheckDuplicateUserNicknameValidator checkDuplicateUserNicknameValidator;
    private final CheckUserExistByIdValidator checkUserExistByIdValidator;
    private final CheckPasswordValidator checkPasswordValidator;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.signUpUserFunction = new SignUpUserFunction(userRepository);
        this.findUserByIdFunction = new FindUserByIdFunction(userRepository);
        this.checkDuplicateUserNicknameValidator = new CheckDuplicateUserNicknameValidator(userRepository);
        this.checkDuplicateUserEmailValidator = new CheckDuplicateUserEmailValidator(userRepository);
        this.checkUserExistByIdValidator = new CheckUserExistByIdValidator(userRepository);
        this.checkPasswordValidator = new CheckPasswordValidator();
        this.findUserByEmailFunction = new FindUserByEmailFunction(userRepository);
    }

    public SignInResponse signIn(SignInRequest signInRequest){
        User user = findUserByEmailFunction.apply(signInRequest.getEmail());
        checkPasswordValidator.verify(
                new InputPasswordAndRealPassword(user.getHashedPassword(), signInRequest.getHashedPassword())
        );

        return new SignInResponse(
                new UserInfo(user),
                JwtTokenProvider.getInstance().generateAccessKey(user, secretKey, expiration),
                JwtTokenProvider.getInstance().generateRefreshToken(user, secretKey)
        );
    }

    public SignInResponse signUpByEmail(SignUpRequest signUpRequest){
        checkDuplicateUserEmailValidator.verify(signUpRequest.getEmail());
        checkDuplicateUserNicknameValidator.verify(signUpRequest.getNickname());
        User user = signUpUserFunction.apply(
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
        checkUserExistByIdValidator.verify(userId);

        return JwtTokenProvider.getInstance().generateAccessKey(
                findUserByIdFunction.apply(userId),
                secretKey,
                expiration
        );
    }

    public User getUserByAccessToken(String accessToken){
        Claims claims = JwtTokenProvider.getInstance().decodingToken(accessToken, secretKey);
        Long userId = JwtTokenProvider.getInstance().getUserIdByClaims(claims, "AccessToken");

        return findUserByIdFunction.apply(userId);
    }

    public CheckDuplicateResponse checkDuplicateEmail(String email){
        try {
            checkDuplicateUserEmailValidator.verify(email);
        }catch (EmailIsAlreadyExistError error){
            return new CheckDuplicateResponse(false, "중복된 이메일 입니다.");
        }

        return new CheckDuplicateResponse(true, "중복되지 않은 이메일 입니다.");
    }

    public CheckDuplicateResponse checkDuplicateNickname(String nickname){
        try {
            checkDuplicateUserNicknameValidator.verify(nickname);
        }catch (NicknameIsAlreadyExistError error){
            return new CheckDuplicateResponse(false, "중복된 닉네임 입니다.");
        }

        return new CheckDuplicateResponse(true, "중복되지 않은 닉네임 입니다.");
    }
}
