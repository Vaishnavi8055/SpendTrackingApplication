package com.incs.spendtracking.service;

import com.incs.spendtracking.CustomUserDetailService;
import com.incs.spendtracking.common.User;
import com.incs.spendtracking.common.UserWallet;
import com.incs.spendtracking.exception.ValidationException;
import com.incs.spendtracking.repository.LoginDao;
import com.incs.spendtracking.repository.UserRepository;
import com.incs.spendtracking.repository.UserWalletRepository;
import com.incs.spendtracking.repository.WalletRepository;
import com.incs.spendtracking.request.AuthenticationRequest;
import com.incs.spendtracking.request.UserRequest;
import com.incs.spendtracking.request.UserUpdateRequest;
import com.incs.spendtracking.response.utils.ResponseUtil;
import com.incs.spendtracking.utils.CommonUtils;
import com.incs.spendtracking.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository endUserRepository;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private UserWalletRepository userWalletRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

  /*  @Autowired
    private RoleRepository roleRepository;*/

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /*@Autowired
    private CustomUserDetailService customUserDetailService;
*/
    public User register(UserRequest endUserRequest) {

        User endUser = new User();
        endUser.setUserId(CommonUtils.generateUUID());
        endUser.setFirstName(endUserRequest.getFirstName());

        if (Objects.isNull(endUser.getFirstName()) || endUser.getFirstName() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User First Name cannot be null or empty");
        }

        endUser.setLastName(endUserRequest.getLastName());

        if (Objects.isNull(endUser.getLastName()) || endUser.getLastName() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Last Name cannot be null or empty");
        }

        endUser.setUserEmail(endUserRequest.getUserEmail());

        if (Objects.isNull(endUser.getUserEmail()) || endUser.getUserEmail() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Email cannot be null or empty");
        }

        endUser.setUserName(endUserRequest.getUserName());

        if (Objects.isNull(endUser.getUserEmail()) || endUser.getUserEmail() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User First Name cannot be null or empty");
        }

        endUser.setPhoneNumber(endUserRequest.getPhoneNumber());

        if (Objects.isNull(endUser.getPhoneNumber()) || endUser.getPhoneNumber() == "" || endUser.getPhoneNumber().length() > 10) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Phone Number cannot be null, empty or size greater than 10");
        }
        endUser.setPassword(passwordEncoder.encode(endUserRequest.getPassword()));

        if (Objects.isNull(endUser.getPassword()) || endUser.getPassword() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Password cannot be null or empty");
        }
        // endUser.setUserRole(endUserRepository.getUserRoleName(endUserRequest.getRoleName()));

       /* if (endUserRepository.getCountOfUsersInDatabase() < 0){
            throw  new ValidationException(HttpStatus.BAD_REQUEST.value(), "No Users Registered");
        }*/

        if (endUserRepository.getCountOfUsersInDatabase() <= 0) {
            endUser.setUserRoleName("ADMIN");
        } else {
            endUser.setUserRoleName("USER");
        }

        endUser.setEnabled("ACTIVE");

        if (endUser.getUserRoleName().equals("USER")) {
            UserWallet userWallet = new UserWallet();
            userWallet.setUserWalletId(CommonUtils.generateUUID());
            userWallet.setUserWalletType(endUserRequest.getUserWalletType());
            userWallet.setUserWalletCredit(walletRepository.getWalletCredit(endUserRequest.getUserWalletType()));
            userWallet.setWallet(walletRepository.getWallet(endUserRequest.getUserWalletType()));
            userWallet.setUser(endUser);
            userWalletRepository.save(userWallet);
        } else {
            UserWallet adminUserWallet = new UserWallet();
            adminUserWallet.setUserWalletId(CommonUtils.generateUUID());
            adminUserWallet.setUserWalletType("PREMIUM");
            adminUserWallet.setUserWalletCredit(60000);
            adminUserWallet.setWallet(walletRepository.getWallet(endUserRequest.getUserWalletType()));
            adminUserWallet.setUser(endUser);
            userWalletRepository.save(adminUserWallet);
        }


        // save
        endUserRepository.save(endUser);

        return endUser;
    }

    public String generateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }

        User userData = endUserRepository.findByUserName(authenticationRequest.getUserName());
        return jwtUtil.generateToken(authenticationRequest.getUserName(), userData);
    }

    public User updateUserProfile(UserUpdateRequest userUpdateRequest, String userName) {

        // User existingUser = endUserRepository.findByUserId(user.getUserId());
        User existingUser = endUserRepository.findByUserName(userName);

        if (Objects.isNull(existingUser)) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Does not exist! PLease register yourself");
        }

        existingUser.setUserEmail(userUpdateRequest.getUserEmail());

        if (Objects.isNull(existingUser.getUserEmail()) || existingUser.getUserEmail() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Email cannot be null or empty");
        }

        existingUser.setFirstName(userUpdateRequest.getFirstName());

        if (Objects.isNull(existingUser.getFirstName()) || existingUser.getFirstName() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User FirstName cannot be null or empty");
        }

        existingUser.setLastName(userUpdateRequest.getLastName());

        if (Objects.isNull(existingUser.getLastName()) || existingUser.getLastName() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User LastName cannot be null or empty");
        }

        existingUser.setPassword(userUpdateRequest.getPassword());

        if (Objects.isNull(existingUser.getPassword()) || existingUser.getPassword() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Password cannot be null or empty");
        }

        existingUser.setUserName(userUpdateRequest.getUserName());

        if (Objects.isNull(existingUser.getUserName()) || existingUser.getUserName() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "UserName cannot be null or empty");
        }

        existingUser.setPhoneNumber(userUpdateRequest.getPhoneNumber());

        if (Objects.isNull(existingUser.getPhoneNumber()) || existingUser.getPhoneNumber() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Phone Number cannot be null or empty");
        }

        return endUserRepository.save(existingUser);
    }

    public String deleteUser(String userName) {
        User user = endUserRepository.findByUserName(userName);

        if (Objects.isNull(user)) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User Does not exist! PLease register yourself");
        }
        endUserRepository.delete(user);
        return userName + " account is deleted";
    }

    public Integer viewUserWallet(String userId) {

        User user = endUserRepository.getById(userId);
        if (user == null) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "User with id" + userId + " does not exist");
        }
        Integer userWalletCredit = userWalletRepository.getUserWalletCredit(userId);

        return userWalletCredit;
    }


}
