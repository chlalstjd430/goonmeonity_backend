package com.goonmeonity.external.api.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RegisterInstallmentSavingsInfoRequest {
    private String name;

    private int payment;

    private double interestRate;

    private int paymentDay;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate dueDate;
}