package com.incs.spendtracking.repository;

import com.incs.spendtracking.common.UserWallet;
import com.incs.spendtracking.common.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletRepository extends JpaRepository<Wallet , String> {

    @Query("Select walletCredit from Wallet where walletType=:userWalletType")
    Integer getWalletCredit(@Param(value = "userWalletType") String userWalletType);

    @Query("from Wallet where walletType=:walletType")
    Wallet getWallet(@Param(value = "walletType") String walletType);
}
