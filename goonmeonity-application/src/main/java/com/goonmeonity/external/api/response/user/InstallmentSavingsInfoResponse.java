package com.goonmeonity.external.api.response.user;

import com.goonmeonity.domain.entity.user.UserInstallmentSavings;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class InstallmentSavingsInfoResponse {
    private long id;
    private String name;
    private int payment;
    private double interestRate;
    private int paymentDay;
    private LocalDate startDate;
    private LocalDate dueDate;

    public InstallmentSavingsInfoResponse(UserInstallmentSavings userInstallmentSavings){
        this.id = userInstallmentSavings.getId();
        this.name = userInstallmentSavings.getName();
        this.payment = userInstallmentSavings.getPayment();
        this.interestRate = userInstallmentSavings.getInterestRate();
        this.paymentDay = userInstallmentSavings.getPayment();
        this.startDate = userInstallmentSavings.getStartDate();
        this.dueDate = userInstallmentSavings.getDueDate();
    }

}
