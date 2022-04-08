package com.incs.spendtracking.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @JsonIgnore
    @Id
    @Column(name = "user_id", length = 1000)
    private String userId;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String userName;

    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @JsonIgnore
    @Column(nullable = false, length = 200)
    private String password;

    @Column(name = "phone_number", unique = true, nullable = false, length = 10)
    private String phoneNumber;

    @Column(name = "user_email", nullable = false, unique = true, length = 50)
    private String userEmail;

    @Column(name = "user_enabled", nullable = false)
    private String enabled;

    @Column(name = "user_role_name")
    private String userRoleName;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserWallet userWallet;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

    public UserWallet getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(UserWallet userWallet) {
        this.userWallet = userWallet;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

}
