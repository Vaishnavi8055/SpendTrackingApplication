package com.incs.spendtracking.controller;

import com.incs.spendtracking.request.WalletRequest;
import com.incs.spendtracking.response.ApiResponseDTO;
import com.incs.spendtracking.response.generic.AccessDeniedResponseDTO;
import com.incs.spendtracking.response.generic.BadRequestResponseDTO;
import com.incs.spendtracking.response.generic.NotAuthenticatedResponseDTO;
import com.incs.spendtracking.response.generic.ResponseDTO;
import com.incs.spendtracking.response.utils.ResponseUtil;
import com.incs.spendtracking.service.WalletService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private ResponseUtil responseUtil;

    @ApiOperation(value = "Insert Wallet Subscription Data")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PostMapping("/addWallet")
    public ResponseDTO<?> addWallet(@RequestBody WalletRequest walletRequest) {
        walletService.insertIntoWallet(walletRequest);
        return responseUtil.ok(HttpStatus.BAD_REQUEST.value());
    }
}
