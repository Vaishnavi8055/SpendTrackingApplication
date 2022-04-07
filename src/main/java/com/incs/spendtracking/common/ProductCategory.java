package com.incs.spendtracking.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @JsonIgnore
    @Id
    @Column(name = "product_category_id" , length = 1000)
    private String productCategoryId;

    @Column(name = "product_category_name")
    private String productCategoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "productCategory")
    private Set<Product> productSet;

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }
}
