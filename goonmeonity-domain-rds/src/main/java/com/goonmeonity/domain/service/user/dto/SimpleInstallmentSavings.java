package com.goonmeonity.domain.service.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goonmeonity.domain.entity.user.UserInstallmentSavings;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SimpleInstallmentSavings {
    private long id;
    private String name;
    private int payment;
    private int amount;
    private double interestRate;
    private int paymentDay;

    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate dueDate;

    public SimpleInstallmentSavings(UserInstallmentSavings userInstallmentSavings){
        this.id = userInstallmentSavings.getId();
        this.name = userInstallmentSavings.getName();
        this.payment = userInstallmentSavings.getPayment();
        this.amount = userInstallmentSavings.getAmount();
        this.interestRate = userInstallmentSavings.getInterestRate();
        this.paymentDay = userInstallmentSavings.getPayment();
        this.startDate = userInstallmentSavings.getStartDate();
        this.dueDate = userInstallmentSavings.getDueDate();
    }
}
