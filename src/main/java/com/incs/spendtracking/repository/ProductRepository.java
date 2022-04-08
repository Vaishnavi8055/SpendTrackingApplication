package com.incs.spendtracking.repository;

import com.incs.spendtracking.common.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product , String> {

    @Query("from ProductCategory where productCategoryName=:productCategoryName")
    Product getProductByProductCategory(@Param(value = "productCategoryName") String productCategoryName);

    @Query("Select productQuantity from Product where productId=:productId")
    Integer getProductQuantity(String productId);

    @Query(value = "Select * from product p inner join product_category pc where p.productCategoryId = pc.product_category_id limit 5", nativeQuery = true)
    Set<Product> findTop2ByProductCategoryIdOrderByProductPriceAsc(String productCategoryId);


}
