package com.incs.spendtracking.repository;

//import com.incs.spendtracking.common.ProductPurchase;

import com.incs.spendtracking.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends JpaRepository<User, Integer> {

    @Query("From User where userName=:userName and password=:password")
    User findByUserNameAndPassword(@Param(value = "userName") String username , @Param(value = "password") String password);
}
