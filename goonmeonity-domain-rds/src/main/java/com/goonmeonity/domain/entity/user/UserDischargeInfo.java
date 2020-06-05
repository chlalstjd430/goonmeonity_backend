package com.goonmeonity.domain.entity.user;

import com.goonmeonity.domain.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDischargeInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated
    @Column(nullable = false)
    private MilitaryAffiliate militaryAffiliate;

    @Enumerated
    @Column(nullable = false)
    private MilitaryStatus militaryStatus;

    @Column(nullable = false)
    private LocalDate enlistmentDate;

    @Column(nullable = false)
    private LocalDate dischargeDate;

    @Builder
    public UserDischargeInfo(User user, MilitaryAffiliate militaryAffiliate, MilitaryStatus militaryStatus, LocalDate enlistmentDate, LocalDate dischargeDate) {
        this.user = user;
        this.militaryAffiliate = militaryAffiliate;
        this.militaryStatus = militaryStatus;
        this.enlistmentDate = enlistmentDate;
        this.dischargeDate = dischargeDate;
    }

    public void setMilitaryAffiliate(MilitaryAffiliate militaryAffiliate) {
        this.militaryAffiliate = militaryAffiliate;
    }

    public void setMilitaryStatus(MilitaryStatus militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    public void setEnlistmentDate(LocalDate enlistmentDate) {
        this.enlistmentDate = enlistmentDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}
