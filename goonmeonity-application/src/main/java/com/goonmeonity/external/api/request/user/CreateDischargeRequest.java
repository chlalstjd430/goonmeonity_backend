package com.goonmeonity.external.api.request.user;

import com.goonmeonity.domain.entity.user.MilitaryAffiliate;
import com.goonmeonity.domain.entity.user.MilitaryStatus;
import com.goonmeonity.external.api.error.ClientRequestIsInvalidError;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateDischargeRequest {
    private MilitaryStatus militaryStatus;
    private MilitaryAffiliate militaryAffiliate;
    private int enlistmentYear;
    private int enlistmentMonth;
    private int enlistmentDay;
    private int dischargeYear;
    private int dischargeMonth;
    private int dischargeDay;

    public LocalDate getEnlistmentDate(){
        try{
            return LocalDate.of(enlistmentYear, enlistmentMonth, enlistmentDay);
        }
        catch (Exception e){
            throw new ClientRequestIsInvalidError("enlistment date format is error");
        }
    }

    public LocalDate getDischargeDate(){
        try{
            return LocalDate.of(dischargeYear, dischargeMonth, dischargeDay);
        }
        catch (Exception e){
            throw new ClientRequestIsInvalidError("discharge date format is error");
        }
    }
}
