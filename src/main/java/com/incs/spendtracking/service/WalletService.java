package com.incs.spendtracking.service;

import com.incs.spendtracking.common.Wallet;
import com.incs.spendtracking.repository.WalletRepository;
import com.incs.spendtracking.request.WalletRequest;
import com.incs.spendtracking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet insertIntoWallet(WalletRequest walletRequest) {

        Wallet wallet = new Wallet();
        wallet.setWalletId(CommonUtils.generateUUID());
        wallet.setWalletType(walletRequest.getWalletType());
        wallet.setWalletCredit(walletRequest.getWalletCredit());

        walletRepository.save(wallet);
        return wallet;
    }
}
