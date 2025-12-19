package com.stylezone.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "shipping", length = 20)
    private String shipping;

    @Column(name = "total", length = 50)
    private String total;

    @Column(name = "status")
    private String status;
}