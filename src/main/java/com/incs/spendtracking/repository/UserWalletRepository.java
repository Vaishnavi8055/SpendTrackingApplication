package com.incs.spendtracking.repository;

import com.incs.spendtracking.common.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet , String> {

    @Query("from UserWallet where userId=:userId")
    Optional<UserWallet> findByUserId(@Param(value = "userId") String userId);

    @Query("Select userWalletCredit from UserWallet where userId=:userId")
    Integer getUserWalletCredit(@Param(value = "userId") String userId);
}
