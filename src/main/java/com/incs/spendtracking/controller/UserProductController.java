package com.incs.spendtracking.controller;

import com.incs.spendtracking.common.Product;
import com.incs.spendtracking.response.utils.ResponseUtil;
import com.incs.spendtracking.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping("/getByCategory/{productCategory}")
    public Optional<Product> viewProductByCategory(@PathVariable String productCategoryName) {
        return productService.getProductByCategory(productCategoryName);
    }
}
