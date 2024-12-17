package com.example.moutis.orderservice.service;

import com.example.moutis.orderservice.model.Product;
import com.example.moutis.orderservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) throws Exception {
        validateProduct(product);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(String id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setValue(productDetails.getValue());
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    private void validateProduct(Product product) throws Exception {
        if (product.getId() != null && productRepository.existsById(product.getId())) {
            throw new Exception("Product with the same ID already exists");
        }
    }
}