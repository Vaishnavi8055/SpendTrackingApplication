package com.incs.spendtracking.repository;

import com.incs.spendtracking.common.ProductPurchaseHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseHistoryRepository  extends MongoRepository<ProductPurchaseHistory , String> {

}
