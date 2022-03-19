package com.knoldus.productservice.service;

import com.knoldus.productservice.model.Product;
import com.knoldus.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid product Id: "+id));
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    public Product update(Product product,Long id){
        Product updatedProduct = this.getProductById(id);
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setImagePath(product.getImagePath());
        return updatedProduct;
    }
    public void createAll(List<Product> productList){
        productRepository.saveAll(productList);
    }
}
