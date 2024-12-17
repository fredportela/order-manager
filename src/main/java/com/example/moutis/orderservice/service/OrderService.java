package com.example.moutis.orderservice.service;

import com.example.moutis.orderservice.model.Order;
import com.example.moutis.orderservice.model.Product;
import com.example.moutis.orderservice.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Operation(summary = "Retrieve all orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(Order order) throws Exception {
        validateOrder(order);
        order.setValue(calculateTotalValue(order));
        return orderRepository.save(order);
    }


    @Transactional
    public Order updateOrder(String id, Order orderDetails) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setNameCustomer(orderDetails.getNameCustomer());
        order.setDescription(orderDetails.getDescription());
        order.setProducts(orderDetails.getProducts());
        order.setValue(calculateTotalValue(orderDetails));
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(String id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }

    private double calculateTotalValue(Order order) {
        return order.getProducts().stream().mapToDouble(product -> product.getValue()).sum();
    }

    private void validateOrder(Order order) throws Exception {
        if (order.getId() != null &&  orderRepository.existsById(order.getId())) {
            throw new Exception("Order with the same ID already exists");
        }
        Set<String> productIds = new HashSet<>();
        for (Product product : order.getProducts()) {
            if (!productIds.add(product.getId())) {
                throw new Exception("Duplicate product found: " + product.getId());
            }
        }
    }
}
