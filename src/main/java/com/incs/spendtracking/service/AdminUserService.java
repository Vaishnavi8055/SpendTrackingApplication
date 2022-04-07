package com.incs.spendtracking.service;

import com.incs.spendtracking.common.User;
import com.incs.spendtracking.exception.ValidationException;
import com.incs.spendtracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private UserRepository userRepository;

    public User setUserDisable(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user.getEnabled().equals("ACTIVE")) {
            user.setEnabled("DISABLED");
        } else {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), user.getUserName() + " is already disabled ");
        }
        return userRepository.save(user);
    }

    public User setUserActive(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user.getEnabled().equals("DISABLED")) {
            user.setEnabled("ACTIVE");
        } else {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), user.getUserName() + " is already active ");
        }
        return userRepository.save(user);
    }


    public User setUserAdmin(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user.getUserRoleName().equals("USER")) {
            user.setUserRoleName("ADMIN");
        } else {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), user.getUserName() + " is already an admin");
        }

        return userRepository.save(user);
    }

    public User setUser_USER(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user.getUserRoleName().equals("ADMIN")) {
            user.setUserRoleName("USER");
        } else {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), user.getUserName() + " is already an admin");
        }

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
