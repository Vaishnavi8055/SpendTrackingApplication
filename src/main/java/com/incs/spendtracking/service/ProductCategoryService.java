
package com.incs.spendtracking.service;

import com.incs.spendtracking.common.ProductCategory;
import com.incs.spendtracking.exception.ValidationException;
import com.incs.spendtracking.repository.ProductCategoryRepository;
import com.incs.spendtracking.request.ProductCategoryRequest;
import com.incs.spendtracking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    public ProductCategory addProductCategory(ProductCategoryRequest productCategoryRequest) {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(CommonUtils.generateUUID());
        productCategory.setProductCategoryName(productCategoryRequest.getProductCategoryName());

        if (productCategoryRequest.getProductCategoryName() == ""){
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product Category Name cannot be empty");
        }

        if (Objects.isNull(productCategory)){
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product Category value cannot be null");
        }
        return productCategoryRepository.save(productCategory);
    }


}
