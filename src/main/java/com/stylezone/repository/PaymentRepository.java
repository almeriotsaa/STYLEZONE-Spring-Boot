package com.stylezone.repository;

import com.stylezone.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByMethod(String method);

    List<Payment> findByStatus(String status);

    List<Payment> findByOrderId(Integer orderId);

    List<Payment> findByMethodAndStatus(String method, String status);
}