package com.incs.spendtracking.controller;

import com.incs.spendtracking.enums.ApiResponseCode;
import com.incs.spendtracking.response.generic.ResponseDTO;
import com.incs.spendtracking.response.utils.ResponseUtil;
import com.incs.spendtracking.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private ResponseUtil responseUtil;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/disableUser/{userId}")
    public ResponseDTO<?> setUserDisable(@PathVariable String userId) {
        return responseUtil.ok(adminUserService.setUserDisable(userId), ApiResponseCode.SUCCESS);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/activeUser/{userId}")
    public ResponseDTO<?> setUserActive(@PathVariable String userId) {
        return responseUtil.ok(adminUserService.setUserActive(userId), ApiResponseCode.SUCCESS);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/adminUser/{userId}")
    public ResponseDTO<?> setUserAdmin(@PathVariable String userId) {
        return responseUtil.ok(adminUserService.setUserAdmin(userId), ApiResponseCode.SUCCESS);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/EndUser/{userId}")
    public ResponseDTO<?> setUser_USER(@PathVariable String userId) {
        return responseUtil.ok(adminUserService.setUser_USER(userId), ApiResponseCode.SUCCESS);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseDTO<?> getAllUsers() {
        return responseUtil.ok(adminUserService.getAllUsers(), ApiResponseCode.SUCCESS);
    }


}
