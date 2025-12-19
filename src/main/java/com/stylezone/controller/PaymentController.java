package com.stylezone.controller;

import com.stylezone.model.Payment;
import com.stylezone.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // GET semua payment atau filter method & status
    @GetMapping
    public List<Payment> list(
            @RequestParam(required = false) String method,
            @RequestParam(required = false) String status) {
        return service.search(method, status);
    }

    // GET detail payment berdasarkan id
    @GetMapping("/{id}")
    public Payment detail(@PathVariable Integer id) {
        return service.findById(id);
    }

    // DELETE payment berdasarkan id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}