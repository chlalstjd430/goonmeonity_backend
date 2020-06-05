package com.goonmeonity.external.api.service;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.entity.user.UserDischargeInfo;
import com.goonmeonity.domain.repository.user.UserDischargeInfoRepository;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.user.error.UserDoesNotHaveDischargeInfoError;
import com.goonmeonity.domain.service.user.function.FindUserDischargeInfoByUserIdFunction;
import com.goonmeonity.domain.service.user.function.SaveUserDischargeInfoFunction;
import com.goonmeonity.external.api.request.user.CreateDischargeRequest;
import com.goonmeonity.external.api.response.user.DischargeInfoResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDischargeInfoRepository userDischargeInfoRepository;

    private final SaveUserDischargeInfoFunction saveUserDischargeInfoFunction;
    private final FindUserDischargeInfoByUserIdFunction findUserDischargeInfoByUserIdFunction;

    public UserService(UserRepository userRepository, UserDischargeInfoRepository userDischargeInfoRepository) {
        this.userRepository = userRepository;
        this.userDischargeInfoRepository = userDischargeInfoRepository;

        this.saveUserDischargeInfoFunction = new SaveUserDischargeInfoFunction(userDischargeInfoRepository);
        this.findUserDischargeInfoByUserIdFunction = new FindUserDischargeInfoByUserIdFunction(userDischargeInfoRepository);
    }

    public DischargeInfoResponse registerDischargeInfo(CreateDischargeRequest createDischargeRequest, User user){
        UserDischargeInfo dischargeInfo;

        try {
            dischargeInfo = findUserDischargeInfoByUserIdFunction.apply(user.getId());
            dischargeInfo.setDischargeDate(createDischargeRequest.getDischargeDate());
            dischargeInfo.setEnlistmentDate(createDischargeRequest.getEnlistmentDate());
            dischargeInfo.setMilitaryAffiliate(createDischargeRequest.getMilitaryAffiliate());
            dischargeInfo.setMilitaryStatus(createDischargeRequest.getMilitaryStatus());
        } catch (UserDoesNotHaveDischargeInfoError error){
            dischargeInfo = UserDischargeInfo.builder()
                    .user(user)
                    .dischargeDate(createDischargeRequest.getDischargeDate())
                    .enlistmentDate(createDischargeRequest.getEnlistmentDate())
                    .militaryAffiliate(createDischargeRequest.getMilitaryAffiliate())
                    .militaryStatus(createDischargeRequest.getMilitaryStatus())
                    .build();
        }
        UserDischargeInfo userDischargeInfo = saveUserDischargeInfoFunction.apply(dischargeInfo);


        return new DischargeInfoResponse(userDischargeInfo);
    }

    public DischargeInfoResponse getDischargeInfo(User user){
        UserDischargeInfo dischargeInfo = findUserDischargeInfoByUserIdFunction.apply(user.getId());

        return new DischargeInfoResponse(dischargeInfo);
    }
}
