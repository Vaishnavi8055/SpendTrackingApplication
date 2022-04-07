package com.incs.spendtracking.repository;

/*
import com.incs.spendtracking.common.Role;
*/
import com.incs.spendtracking.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

   /* @Query("Select userId from user where userName=:userName")
    String findByUserId(@Param(value = "userName") String userName);
*/
   /* @Query("from Role where roleName=:roleName")
    public Role getUserRoleName(@Param("roleName") String roleName);
*/
    @Query("From User where userName=:userName")
    User findByUserName(@Param(value = "userName") String userName);

    @Query("Select Count(userId) from User")
    Integer getCountOfUsersInDatabase();

    String findIdByUserName(String userName);
}
