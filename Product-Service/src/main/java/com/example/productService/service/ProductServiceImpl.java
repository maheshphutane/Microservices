package com.example.productService.service;

import com.example.productService.exception.ProductServiceException;
import com.example.productService.model.Product;
import com.example.productService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
        return;
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) return null;
        return product.get();
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity of product with Id: "+productId+" by "+quantity);
        Optional<Product> p = productRepository.findById(productId);
        if(p.isPresent()){
            Product product = p.get();
            long availableQuantity = product.getQuantity();
            if(availableQuantity<quantity) {
                throw new ProductServiceException("Product with id "+productId+" is Not available with given quantity", "PRODUCT_UNAVAILABLE");
            }
            product.setQuantity(availableQuantity-quantity);
            productRepository.save(product);
            log.info("Product Quantity updated successfully");
        }else throw new ProductServiceException("Product with id "+productId+" Not Found", "PRODUCT_NOT_FOUND");
    }
}
