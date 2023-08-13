package com.example.productService.service;

import com.example.productService.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    void saveProduct(Product product);

    Product getProductById(long id);

    void reduceQuantity(long productId, long quantity);
}
