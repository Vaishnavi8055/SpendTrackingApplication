package com.incs.spendtracking.controller;

import com.incs.spendtracking.common.Product;
import com.incs.spendtracking.enums.ApiResponseCode;
import com.incs.spendtracking.request.ProductRequest;
import com.incs.spendtracking.request.ProductUpdateQuantityRequest;
import com.incs.spendtracking.request.ProductUpdateRequest;
import com.incs.spendtracking.response.ApiResponseDTO;
import com.incs.spendtracking.response.generic.AccessDeniedResponseDTO;
import com.incs.spendtracking.response.generic.BadRequestResponseDTO;
import com.incs.spendtracking.response.generic.NotAuthenticatedResponseDTO;
import com.incs.spendtracking.response.generic.ResponseDTO;
import com.incs.spendtracking.response.utils.ResponseUtil;
import com.incs.spendtracking.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseUtil responseUtil;


    @ApiOperation(value = "Insert Product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseDTO<?> addProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
        return responseUtil.ok(ApiResponseCode.SUCCESS);
    }


    @ApiOperation(value = "Update Product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{productId}")
    public ResponseDTO<?> updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest, @PathVariable String productId) {
        productService.updateProduct(productUpdateRequest, productId);
        return responseUtil.ok(ApiResponseCode.SUCCESS);
    }


    @ApiOperation(value = "Get ALl Products")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAll")
    public ResponseDTO<?> getAllProducts() {
        return responseUtil.ok(productService.getAllProducts(), ApiResponseCode.SUCCESS);
    }

    @ApiOperation(value = "Get Product By ID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getById/{productId}")
    public Optional<Product> getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
        /*return responseUtil.ok(ApiResponseCode.SUCCESS);*/
    }

    @ApiOperation(value = "Delete Product By Id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteById/{productId}")
    public ResponseDTO<?> deleteProductById(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return responseUtil.ok(ApiResponseCode.SUCCESS);
    }


    @ApiOperation(value = "Update Product Quantity By Product Id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateById/{productId}")
    public ResponseDTO<?> updateProductQuantityById(@RequestBody ProductUpdateQuantityRequest productUpdateQuantityRequest, @PathVariable String productId) {
        productService.updateProductQuantity(productUpdateQuantityRequest, productId);
        return responseUtil.ok(ApiResponseCode.SUCCESS);
    }

    @ApiOperation(value = "View Product By Category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ApiResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = BadRequestResponseDTO.class),
            @ApiResponse(code = 401, message = "You are Not Authenticated", response = NotAuthenticatedResponseDTO.class),
            @ApiResponse(code = 403, message = "Not Authorized on this resource", response = AccessDeniedResponseDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    // max 5
    @GetMapping("/getByCategoryId/{productCategoryId}")
    public ResponseDTO<?> getProductByProductCategoryId(@PathVariable String productCategoryId) {
        return responseUtil.ok(productService.getProductByProductCategoryId(productCategoryId), ApiResponseCode.SUCCESS);
    }
}
