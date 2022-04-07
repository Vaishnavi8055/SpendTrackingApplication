package com.incs.spendtracking.request;

public class WalletRequest {

    private String walletType;
    private Integer walletCredit;

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public Integer getWalletCredit() {
        return walletCredit;
    }

    public void setWalletCredit(Integer walletCredit) {
        this.walletCredit = walletCredit;
    }
}
