package com.incs.spendtracking.repository;

import com.incs.spendtracking.common.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, String> {

    @Query("from Authentication where userName=:userName")
    Authentication findByUserName(@Param(value = "userName") String username);
}
