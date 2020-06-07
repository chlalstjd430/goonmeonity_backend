package com.goonmeonity.domain.repository.user;

import com.goonmeonity.domain.entity.user.UserInstallmentSavings;
import com.goonmeonity.domain.repository.ExtendRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInstallmentSavingsRepository extends ExtendRepository<UserInstallmentSavings> {

    List<UserInstallmentSavings> findAllByUserId(long userId);

    Optional<UserInstallmentSavings> findByIdAndUserId(long id, long userId);
}
