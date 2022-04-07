package com.incs.spendtracking.controller;

import com.incs.spendtracking.enums.ApiResponseCode;
import com.incs.spendtracking.request.UserWalletRequest;
import com.incs.spendtracking.response.ApiResponseDTO;
import com.incs.spendtracking.response.generic.AccessDeniedResponseDTO;
import com.incs.spendtracking.response.generic.BadRequestResponseDTO;
import com.incs.spendtracking.response.generic.NotAuthenticatedResponseDTO;
import com.incs.spendtracking.response.generic.ResponseDTO;
import com.incs.spendtracking.response.utils.ResponseUtil;
import com.incs.spendtracking.service.UserWalletService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/wallet")
public class UserWalletController {

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private UserWalletService userWalletService;

    @ApiOperation(value = "Update User Wallet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{userId}")
    public ResponseDTO<?> updateUserWallet(@RequestBody UserWalletRequest userWalletRequest, @PathVariable String userId) {
        userWalletService.updateUserWallet(userWalletRequest, userId);
        return responseUtil.ok(ApiResponseCode.SUCCESS);
    }

    @ApiOperation(value = "Get User Wallet Credit")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getCredit/{userId}")
    public String getUserWalletCredit(@PathVariable String userId){
        return userWalletService.getUserWalletCredit(userId);
    }


}
