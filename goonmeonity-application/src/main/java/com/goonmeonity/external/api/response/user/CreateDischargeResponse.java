package com.goonmeonity.external.api.response.user;

import com.goonmeonity.domain.entity.user.MilitaryAffiliate;
import com.goonmeonity.domain.entity.user.MilitaryStatus;
import com.goonmeonity.domain.entity.user.UserDischargeInfo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateDischargeResponse {
    private MilitaryStatus militaryStatus;
    private MilitaryAffiliate militaryAffiliate;
    private LocalDate enlistmentDate;
    private LocalDate dischargeDate;

    public CreateDischargeResponse(UserDischargeInfo userDischargeInfo) {
        this.militaryStatus = userDischargeInfo.getMilitaryStatus();
        this.militaryAffiliate = userDischargeInfo.getMilitaryAffiliate();
        this.enlistmentDate = userDischargeInfo.getEnlistmentDate();
        this.dischargeDate = userDischargeInfo.getDischargeDate();
    }
}
