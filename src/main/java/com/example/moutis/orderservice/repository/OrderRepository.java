package com.example.moutis.orderservice.repository;

import com.example.moutis.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    // Métodos personalizados, se necessário
}
