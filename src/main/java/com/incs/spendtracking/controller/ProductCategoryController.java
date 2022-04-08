package com.incs.spendtracking.controller;

import com.incs.spendtracking.enums.ApiResponseCode;
import com.incs.spendtracking.request.ProductCategoryRequest;
import com.incs.spendtracking.response.ApiResponseDTO;
import com.incs.spendtracking.response.generic.AccessDeniedResponseDTO;
import com.incs.spendtracking.response.generic.BadRequestResponseDTO;
import com.incs.spendtracking.response.generic.NotAuthenticatedResponseDTO;
import com.incs.spendtracking.response.generic.ResponseDTO;
import com.incs.spendtracking.response.utils.ResponseUtil;
import com.incs.spendtracking.service.ProductCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ResponseUtil responseUtil;


    @ApiOperation(value = "Insert Product Category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseDTO<?> addProductCategory(@RequestBody ProductCategoryRequest productCategoryRequest) {
        productCategoryService.addProductCategory(productCategoryRequest);
        return responseUtil.ok(ApiResponseCode.SUCCESS);
    }

}
