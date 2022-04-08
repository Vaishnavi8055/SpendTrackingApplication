package com.incs.spendtracking.service;

import com.incs.spendtracking.CustomUserDetailService;
import com.incs.spendtracking.common.User;
import com.incs.spendtracking.common.UserHistory;
import com.incs.spendtracking.common.UserWallet;
import com.incs.spendtracking.exception.ValidationException;
import com.incs.spendtracking.repository.*;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
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

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    public void userHistoryActivity(String userId, String userName, String description) {
        UserHistory userHistory = new UserHistory();
        userHistory.setUserHistoryId(CommonUtils.generateUUID());
        userHistory.setUserId(userId);
        userHistory.setDescription(description);
        userHistory.setActivityDoneAt(LocalDateTime.now());
        userHistory.setDescription(description);
        userHistory.setUserName(userName);

        userHistoryRepository.save(userHistory);
    }

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
        if (endUserRepository.getCountOfUsersInDatabase() <= 0) {
            endUser.setUserRoleName("ADMIN");
        } else {
            endUser.setUserRoleName("USER");
        }

        endUser.setEnabled("ACTIVE");

        UserWallet userWallet = new UserWallet();
        userWallet.setUserWalletId(CommonUtils.generateUUID());
        userWallet.setUserWalletType(endUserRequest.getUserWalletType());
        userWallet.setUserWalletCredit(walletRepository.getWalletCredit(endUserRequest.getUserWalletType()));
        userWallet.setWallet(walletRepository.getWallet(endUserRequest.getUserWalletType()));
        userWallet.setUser(endUser);
        userWalletRepository.save(userWallet);


        userHistoryActivity(endUser.getUserId(), endUser.getUserName(), "User registered");
        // save
        endUserRepository.save(endUser);

        return endUser;
    }

    public String generateToken(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }

        User userData = endUserRepository.findByUserName(authenticationRequest.getUserName());

        userHistoryActivity(userData.getUserId(), userData.getUserName(), "Token Generated");

        if (userData.getEnabled().equals("Disabled")) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "You are disabled by admin, Kindly Contact Admin!!");
        } else
            return jwtUtil.generateToken(authenticationRequest.getUserName(), userData);
    }

    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }

        return "Logout Successfully";
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

        userHistoryActivity(existingUser.getUserId(), existingUser.getUserName(), "User Profile Updated");

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
