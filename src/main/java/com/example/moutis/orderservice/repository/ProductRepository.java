package com.example.moutis.orderservice.repository;

import com.example.moutis.orderservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Métodos personalizados, se necessário
}
