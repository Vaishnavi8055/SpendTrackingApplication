package com.incs.spendtracking.service;

import com.incs.spendtracking.common.Product;
import com.incs.spendtracking.exception.ValidationException;
import com.incs.spendtracking.repository.ProductCategoryRepository;
import com.incs.spendtracking.repository.ProductRepository;
import com.incs.spendtracking.request.ProductRequest;
import com.incs.spendtracking.request.ProductUpdateQuantityRequest;
import com.incs.spendtracking.request.ProductUpdateRequest;
import com.incs.spendtracking.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public Product addProduct(ProductRequest productRequest) {

        Product product = new Product();
        product.setProductId(CommonUtils.generateUUID());
        product.setProductName(productRequest.getProductName());

        if (Objects.isNull(product.getProductName()) || product.getProductName() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product Name cannot be null or empty");
        }

        product.setProductPrice(productRequest.getProductPrice());

        if (Objects.isNull(product.getProductPrice()) || product.getProductPrice() <= 0) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product Price cannot be null or 0");
        }

        product.setProductQuantity(productRequest.getProductQuantity());

        if (Objects.isNull(product.getProductQuantity()) || product.getProductQuantity() < 0) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product Quantity cannot be null or less than 0");
        }

        product.setProductCategory(productCategoryRepository.getCategory(productRequest.getProductCategoryName()));

        if (Objects.isNull(product.getProductCategory())) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product Category cannot be null or empty");
        }
        productRepository.save(product);

        return product;
    }


    public Product updateProduct(ProductUpdateRequest productUpdateRequest, String productId) {

        Product existingProduct = productRepository.findById(String.valueOf(productId)).orElse(null);

        existingProduct.setProductPrice(productUpdateRequest.getProductPrice());

        if (Objects.isNull(existingProduct.getProductPrice()) || existingProduct.getProductPrice() <= 0) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product Price cannot be null or 0");
        }

        existingProduct.setProductName(productUpdateRequest.getProductName());

        if (Objects.isNull(existingProduct.getProductName()) || existingProduct.getProductName() == "") {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product Name cannot be null or empty");
        }

        existingProduct.setProductQuantity(productUpdateRequest.getProductQuantity());

        if (Objects.isNull(existingProduct.getProductQuantity()) || existingProduct.getProductQuantity() < 0) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Product Quantity cannot be null or less than 0");
        }

        return productRepository.save(existingProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(String.valueOf(productId));
    }

    public void deleteProductById(Integer productId) {
        productRepository.deleteById(String.valueOf(productId));
    }

    public Optional<Product> getProductByCategory(String productCategoryName) {
        Product product = productRepository.getProductByProductCategory(productCategoryName);

        if (Objects.isNull(product)) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "No Products left in this category");
        }
        return Optional.of(product);
    }

    public Product updateProductQuantity(ProductUpdateQuantityRequest productUpdateQuantityRequest, String productId) {
        Product product = productRepository.getById(productId);
        product.setProductQuantity(productUpdateQuantityRequest.getProductQuantity());
        return productRepository.save(product);
    }

    public Set<Product> getProductByProductCategoryId(String productCategoryId) {
        Set<Product> productSet = productRepository.findTop2ByProductCategoryIdOrderByProductPriceAsc(productCategoryId);
        return productSet;
    }
}
