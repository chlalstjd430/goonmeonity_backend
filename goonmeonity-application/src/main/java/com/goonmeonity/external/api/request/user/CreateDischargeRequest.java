package com.goonmeonity.external.api.request.user;

import com.goonmeonity.domain.entity.user.MilitaryAffiliate;
import com.goonmeonity.domain.entity.user.MilitaryStatus;
import com.goonmeonity.external.api.error.ClientRequestIsInvalidError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateDischargeRequest {
    private MilitaryStatus militaryStatus;

    private MilitaryAffiliate militaryAffiliate;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate enlistmentDate;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate dischargeDate;
}
