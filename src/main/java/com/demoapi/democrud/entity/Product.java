package com.demoapi.democrud.entity;

import com.demoapi.democrud.enums.Size;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name" , nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String name;

    @Column(name = "description" , columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Size size;

    String color;

    String material; // -- Chất liệu

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal price;

    @Column(nullable = false)
    int stock = 0; // -- Số lượng tồn kho

    String imageUrl;

    String imageUrl2;

    String imageUrl3;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
