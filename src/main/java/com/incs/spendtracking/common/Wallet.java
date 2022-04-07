package com.incs.spendtracking.common;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @Column(name = "wallet_id" , length = 1000)
    private String walletId;

    @Column(name = "wallet_type")
    private String walletType;

    @Column(name = "wallet_credit")
    private Integer walletCredit;

    @OneToMany(mappedBy = "wallet")
    private Set<UserWallet> userWalletSet;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userWalletId")
//    private UserWallet userWallet;

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

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

    public Set<UserWallet> getUserWalletSet() {
        return userWalletSet;
    }

    public void setUserWalletSet(Set<UserWallet> userWalletSet) {
        this.userWalletSet = userWalletSet;
    }
/* public Set<UserWallet> getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(Set<UserWallet> userWallet) {
        this.userWallet = userWallet;
    }*/
}
