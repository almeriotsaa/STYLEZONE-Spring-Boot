package com.stylezone.service;

import com.stylezone.model.Order;
import com.stylezone.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public Order save(Order order) {
        return repo.save(order);
    }

    public List<Order> findAll() {
        return repo.findAll();
    }

    public Order findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Order> search(Integer userId, String status, String shipping) {
        if (userId != null && status != null) {
            return repo.findByUserIdAndStatus(userId, status);
        }
        if (userId != null) {
            return repo.findByUserId(userId);
        }
        if (status != null) {
            return repo.findByStatus(status);
        }
        if (shipping != null) {
            return repo.findByShipping(shipping);
        }
        return repo.findAll();
    }
}