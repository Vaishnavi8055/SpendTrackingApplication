
package com.incs.spendtracking.service;

import com.incs.spendtracking.common.*;
import com.incs.spendtracking.exception.ValidationException;
import com.incs.spendtracking.repository.*;
import com.incs.spendtracking.request.ProductPurchaseRequest;
import com.incs.spendtracking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

// order
@Service
public class ProductPurchaseService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductPurchaseRepository productPurchaseRepository;

    @Autowired
    private UserWalletRepository userWalletRepository;

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;


    public ProductPurchase madePurchase(ProductPurchaseRequest productPurchaseRequest, String userId, String productId) {

        ProductPurchase productPurchase = new ProductPurchase();

        ProductPurchaseHistory productPurchaseHistory = new ProductPurchaseHistory();
        productPurchaseHistory.setPurchaseHistoryId(CommonUtils.generateUUID());

        Product productToBuy = productRepository.getById(productId);
        //check product available or not
        if (Objects.isNull(productToBuy)) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product does not exist !!");
        } else {
            // product initial quantity
            Integer productQuantityBeforePurchase = productRepository.getProductQuantity(productId);
            // check product quantity
            if (productQuantityBeforePurchase < productPurchaseRequest.getQuantity()) {
                throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Sorry!! Product Stock is less than your requirement");
            } else {
                Integer productQuantityRemaining = productQuantityBeforePurchase - productPurchaseRequest.getQuantity();

                productPurchase.setPurchaseQuantity(productPurchaseRequest.getQuantity());
                productPurchaseHistory.setProductQuantity(productPurchaseRequest.getQuantity());
                productToBuy.setProductQuantity(productQuantityRemaining);
                productPurchaseHistory.setProductName(productToBuy.getProductName());

                Integer totalPriceAfterPurchase = productPurchaseRequest.getQuantity() * productToBuy.getProductPrice();
                Integer userWalletCredit = userWalletRepository.getUserWalletCredit(userId);

                UserWallet userWallet = userWalletRepository.findByUserId(userId).orElse(null);
                Integer userWalletCreditsRemaining = userWalletCredit - totalPriceAfterPurchase;

                Integer minimumCredit = 1000;
                if (userWalletCredit < minimumCredit) {
                    throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Your wallet balance is less than the minimum required balance, Kindly renew your subscription !!");
                } else {
                    if (userWalletCredit < totalPriceAfterPurchase) {
                        throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Your wallet credits are too less !");
                    } else {

                        productPurchase.setPurchaseTotalPrice(totalPriceAfterPurchase);

                        productPurchaseHistory.setProductPurchasedPrice(totalPriceAfterPurchase);

                        userWallet.setUserWalletCredit(userWalletCreditsRemaining);
                        userWalletRepository.save(userWallet);
                        //  LocalDateTime localDateTimeOfPurchase = LocalDateTime.now();
                        productPurchase.setPurchaseDateTime(LocalDateTime.now());
                        productPurchaseHistory.setProductBuyAt(LocalDateTime.now());
                        productPurchase.setProduct(productToBuy);

                    }
                }
            }
            User user = userRepository.getById(userId);
            productPurchase.setUser(user);
            productPurchaseHistory.setUserName(user.getUserName());

            productPurchase.setPurchaseId(CommonUtils.generateUUID());
            productPurchase.setPurchaseAddress(productPurchaseRequest.getAddress());

        }


        productRepository.save(productToBuy);
        productPurchaseRepository.save(productPurchase);
        purchaseHistoryRepository.save(productPurchaseHistory);

        return productPurchase;
    }

    public Set<String> getAllProductsPurchased(String userId) {

        List<ProductPurchase> productPurchased = productPurchaseRepository.findAll();
        Set<String> productPurchasedName = new HashSet<>();

        for (ProductPurchase product : productPurchased) {
            productPurchasedName.add(product.getProduct().getProductName());
        }
        return productPurchasedName;
    }

}

