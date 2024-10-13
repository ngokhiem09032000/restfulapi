package com.demoapi.democrud.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Size {

    @EmbeddedId
    private SizeId id; // Sử dụng lớp khóa chính đã tạo

    @Column(nullable = false)
    int stock = 0; // -- Số lượng tồn kho

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId") // Ánh xạ productId trong SizeId tới product
    @JoinColumn(name = "product_id", nullable = false)
    @ToString.Exclude // Bỏ qua product khi gọi toString
    private Product product;
}
