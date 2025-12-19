package com.stylezone.service;

import com.stylezone.model.Payment;
import com.stylezone.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public Payment save(Payment payment) {
        return repo.save(payment);
    }

    public List<Payment> findAll() {
        return repo.findAll();
    }

    public Payment findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Payment> search(String method, String status) {
        if (method != null && status != null) {
            return repo.findByMethodAndStatus(method, status);
        }
        if (method != null) {
            return repo.findByMethod(method);
        }
        if (status != null) {
            return repo.findByStatus(status);
        }
        return repo.findAll();
    }
}