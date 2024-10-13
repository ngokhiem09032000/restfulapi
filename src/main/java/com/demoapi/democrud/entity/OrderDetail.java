package com.demoapi.democrud.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String productId;

    @Column(name = "name" , nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String name;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal price;

    String color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private com.demoapi.democrud.enums.Size size;

    @Column(nullable = false)
    int amount;

    @Column(nullable = false)
    BigDecimal totalPrice;

    String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @ToString.Exclude // Bỏ qua product khi gọi toString
    private Order order;
}
