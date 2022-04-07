package com.incs.spendtracking.common;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @JsonIgnore
    @Id
    @Column(name = "product_id", length = 1000)
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @Column(name = "product_price")
    private Integer productPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productCategoryId")
    private ProductCategory productCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<ProductPurchase> productPurchaseSet;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Set<ProductPurchase> getProductPurchaseSet() {
        return productPurchaseSet;
    }

    public void setProductPurchaseSet(Set<ProductPurchase> productPurchaseSet) {
        this.productPurchaseSet = productPurchaseSet;
    }
}
