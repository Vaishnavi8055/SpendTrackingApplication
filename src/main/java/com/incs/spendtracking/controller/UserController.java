package com.incs.spendtracking.controller;


import com.incs.spendtracking.enums.ApiResponseCode;
import com.incs.spendtracking.request.AuthenticationRequest;
import com.incs.spendtracking.request.UserRequest;
import com.incs.spendtracking.request.UserUpdateRequest;
import com.incs.spendtracking.response.ApiResponseDTO;
import com.incs.spendtracking.response.generic.AccessDeniedResponseDTO;
import com.incs.spendtracking.response.generic.BadRequestResponseDTO;
import com.incs.spendtracking.response.generic.NotAuthenticatedResponseDTO;
import com.incs.spendtracking.response.generic.ResponseDTO;
import com.incs.spendtracking.response.utils.ResponseUtil;
import com.incs.spendtracking.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private UserService userService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    String gethello() {
        return "Hello Admin";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    String getUser() {
        return "Hello User";
    }


    @ApiOperation(value = "Register End User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PostMapping("/register")
    public ResponseDTO<?> register(@RequestBody UserRequest userRequest) {
        System.out.println("Controller called");
        userService.register(userRequest);
        return responseUtil.ok(ApiResponseCode.SUCCESS);
    }

    @ApiOperation(value = "Login End User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PostMapping("/token")
    public String login(@RequestBody AuthenticationRequest loginRequest) throws Exception {
        // System.out.println("Controller called");

        return userService.generateToken(loginRequest);
    }

    @ApiOperation(value = "Update profile of User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PutMapping("/update/{userName}")
    public ResponseDTO<?> updateProfile(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable String userName) {
        userService.updateUserProfile(userUpdateRequest, userName);
        return responseUtil.ok(ApiResponseCode.SUCCESS);
    }

   /* @ApiOperation(value = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @DeleteMapping("/delete/{userName}")
    public ResponseDTO<?>  deleteUserByUserName(@PathVariable String userName) {
        return responseUtil.ok(userService.deleteUser(userName) , ApiResponseCode.SUCCESS);
    }
*/


    @ApiOperation(value = "Get Wallet Credit of User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @GetMapping("/getWalletCredit/{userId}")
    public Integer getWalletCredit(@PathVariable String userId) {
        return userService.viewUserWallet(userId);
    }


}
