package com.knoldus.productservice.controller;

import com.knoldus.productservice.model.Product;
import com.knoldus.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    ProductService productService;
    
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId){
        return ResponseEntity.ok( productService.getProductById(productId));
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        Product createdProduct = productService.createProduct(product);
//        if (createdProduct == null){
//            return ResponseEntity.noContent().build();
//        }
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
//                "{id}").buildAndExpand(createdProduct.getId()).toUri();
//        ResponseEntity.created(location).build();'
        return createdProduct;
    }
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.createProduct(product);
        return ResponseEntity.ok(product);
    }
    @PostMapping("/createAll")
    public void createAllProducts(@RequestBody List<Product> list){
        productService.createAll(list);
    }
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
        Map response= new HashMap();
        response.put("Deleted",true);
        return ResponseEntity.ok(response);
    }
}
