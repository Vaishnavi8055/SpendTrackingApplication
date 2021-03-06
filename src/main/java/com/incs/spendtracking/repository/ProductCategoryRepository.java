package com.incs.spendtracking.repository;

import com.incs.spendtracking.common.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    @Query("from ProductCategory where productCategoryName=:productCategoryName")
    public ProductCategory getCategory(@Param("productCategoryName") String categoryName);

}
