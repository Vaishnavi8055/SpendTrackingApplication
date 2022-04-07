package com.incs.spendtracking.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user_wallet")
public class UserWallet {

    @JsonIgnore
    @Id
    @Column(name = "user_wallet_id" , length = 1000)
    private String userWalletId;

    @Column(name = "user_wallet_type")
    private String userWalletType;

    @Column(name = "user_wallet_credit")
    private Integer userWalletCredit;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "wallet_id" , nullable = false)
    private Wallet wallet;


    public String getUserWalletId() {
        return userWalletId;
    }

    public void setUserWalletId(String userWalletId) {
        this.userWalletId = userWalletId;
    }

    public String getUserWalletType() {
        return userWalletType;
    }

    public void setUserWalletType(String userWalletType) {
        this.userWalletType = userWalletType;
    }

    public Integer getUserWalletCredit() {
        return userWalletCredit;
    }

    public void setUserWalletCredit(Integer userWalletCredit) {
        this.userWalletCredit = userWalletCredit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }


}
