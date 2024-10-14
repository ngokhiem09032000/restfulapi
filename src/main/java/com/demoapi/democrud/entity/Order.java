package com.demoapi.democrud.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "userName" , nullable = false, columnDefinition = "VARCHAR(191) COLLATE utf8mb4_unicode_ci")
    String userName;

    @Column(name = "full_name" , columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String fullName;

    @Column(name = "address" , columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String address;

    @Column(name = "phone" , columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String phone;

    @Column(nullable = false)
    BigDecimal shippingFee;

    @Column(nullable = false)
    BigDecimal total;

    @Column(nullable = false) // tình trạng đơn hàng
    int statusOrder = 0; // 0 chưa thực hiện , 1 đang giao , 2 đã giao thành công

    @Column(nullable = false) // tình trạng thanh toán
    int statusPay = 0; // 0 đang chờ thanh toán , 1 Đã thanh toán

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Bỏ qua orderDetails khi gọi toString
    List<OrderDetail> orderDetails;

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
