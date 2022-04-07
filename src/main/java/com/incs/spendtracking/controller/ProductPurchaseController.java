
package com.incs.spendtracking.controller;

import com.incs.spendtracking.enums.ApiResponseCode;
import com.incs.spendtracking.request.ProductPurchaseRequest;
import com.incs.spendtracking.response.ApiResponseDTO;
import com.incs.spendtracking.response.generic.AccessDeniedResponseDTO;
import com.incs.spendtracking.response.generic.BadRequestResponseDTO;
import com.incs.spendtracking.response.generic.NotAuthenticatedResponseDTO;
import com.incs.spendtracking.response.generic.ResponseDTO;
import com.incs.spendtracking.response.utils.ResponseUtil;
//import com.incs.spendtracking.service.ProductPurchaseService;
import com.incs.spendtracking.service.ProductPurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class ProductPurchaseController {

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private ProductPurchaseService productPurchaseService;

    @ApiOperation(value = "Get ALl Products Purchased")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getAllPurchased/{userId}")
    public ResponseDTO<?> getAllProductsPurchased(@PathVariable String userId) {
        return responseUtil.ok(productPurchaseService.getAllProductsPurchased(userId), ApiResponseCode.SUCCESS);
    }


    @ApiOperation(value = "Purchase Product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/purchase/{userId}/{productId}")
    public ResponseDTO<?> purchaseProduct(@RequestBody ProductPurchaseRequest productPurchaseRequest, @PathVariable String userId, @PathVariable String productId) {
        return responseUtil.ok(productPurchaseService.madePurchase(productPurchaseRequest, userId, productId), ApiResponseCode.SUCCESS);
    }

}

