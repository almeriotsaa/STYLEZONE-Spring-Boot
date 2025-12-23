package com.stylezone.controller;

import com.stylezone.model.Order;
import com.stylezone.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // EXISTING: GET semua order atau filter
    @GetMapping
    public List<Order> list(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String shipping
    ) {
        return service.search(userId, status, shipping);
    }

    // ✅ NEW: search by userId + status + shipping
    @GetMapping("/search")
    public List<Order> searchByUserStatusShipping(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String shipping
    ) {
        return service.search(userId, status, shipping);
    }

    // GET detail order berdasarkan id
    @GetMapping("/{id}")
    public Order detail(@PathVariable Integer id) {
        return service.findById(id);
    }

    // DELETE order berdasarkan id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
