package com.incs.spendtracking.service;

import com.incs.spendtracking.common.UserWallet;
import com.incs.spendtracking.repository.UserWalletRepository;
import com.incs.spendtracking.request.UserWalletRequest;
import com.incs.spendtracking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWalletService {

    @Autowired
    private UserWalletRepository userWalletRepository;

    public UserWallet insertIntoUserWallet(UserWalletRequest userWalletRequest) {

        UserWallet userWallet = new UserWallet();
        userWallet.setUserWalletId(CommonUtils.generateUUID());

        return userWallet;
    }

    public UserWallet updateUserWallet(UserWalletRequest userWalletRequest, String userId) {

        UserWallet existingUserWallet = userWalletRepository.findByUserId(userId).orElse(null);

        existingUserWallet.setUserWalletCredit(userWalletRequest.getUserWalletCredit());
        return userWalletRepository.save(existingUserWallet);
    }

    public String getUserWalletCredit(String userId) {

        Integer balance = userWalletRepository.getUserWalletCredit(userId);

        return "You have " + balance + " credits left in your wallet";
    }


}
