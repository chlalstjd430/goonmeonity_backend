package com.goonmeonity.domain.entity.user;

import com.goonmeonity.domain.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserInstallmentSavings extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private double interestRate;

    @Column(nullable = true)
    private int payment;

    @Column(nullable = true)
    private int amount;

    @Column(nullable = true)
    private int paymentDay;

    @Column(nullable = true)
    private LocalDate startDate;

    @Column(nullable = true)
    private LocalDate dueDate;

    public void setName(String name) {
        this.name = name;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPaymentDay(int paymentDay) {
        this.paymentDay = paymentDay;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
