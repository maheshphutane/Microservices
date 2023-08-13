package com.example.productService.controller;

import com.example.productService.exception.ProductServiceException;
import com.example.productService.model.Product;
import com.example.productService.model.Response;
import com.example.productService.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Response> addProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity<>(new Response("Product "+product.getP_name()+" is created"),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductBy(@PathVariable long id){
        log.info("Get the product for product Id: "+id);
        Product product = productService.getProductById(id);
        if(product==null){
            log.info("Product Not Found for Id: "+id);
            throw new ProductServiceException("Product Not found for the Id: "+id,"PRODUCT_NOT_FOUND");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable(name = "id") long productId,@RequestParam(name = "quantity") long quantity){
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
