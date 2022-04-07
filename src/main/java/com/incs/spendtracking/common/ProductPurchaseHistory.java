package com.incs.spendtracking.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;

@Document(collection = "purchase_history")
public class ProductPurchaseHistory {
    @Id
    private String purchaseHistoryId;
    private String userName;
    private String productName;
    private Integer productQuantity;
    private Integer productPurchasedPrice;
    private LocalDateTime productBuyAt;

    public String getPurchaseHistoryId() {
        return purchaseHistoryId;
    }

    public void setPurchaseHistoryId(String purchaseHistoryId) {
        this.purchaseHistoryId = purchaseHistoryId;
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

    public Integer getProductPurchasedPrice() {
        return productPurchasedPrice;
    }

    public void setProductPurchasedPrice(Integer productPurchasedPrice) {
        this.productPurchasedPrice = productPurchasedPrice;
    }

    public LocalDateTime getProductBuyAt() {
        return productBuyAt;
    }

    public void setProductBuyAt(LocalDateTime productBuyAt) {
        this.productBuyAt = productBuyAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
