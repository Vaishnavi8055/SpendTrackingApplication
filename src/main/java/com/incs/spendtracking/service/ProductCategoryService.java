
package com.incs.spendtracking.service;

import com.incs.spendtracking.common.ProductCategory;
import com.incs.spendtracking.repository.ProductCategoryRepository;
import com.incs.spendtracking.request.ProductCategoryRequest;
import com.incs.spendtracking.request.ProductRequest;
import com.incs.spendtracking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    public ProductCategory addProductCategory(ProductCategoryRequest productCategoryRequest) {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(CommonUtils.generateUUID());
        productCategory.setProductCategoryName(productCategoryRequest.getProductCategoryName());

        return productCategoryRepository.save(productCategory);
    }


}
