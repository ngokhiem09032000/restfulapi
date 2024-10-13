package com.demoapi.democrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeId implements Serializable {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private com.demoapi.democrud.enums.Size name; // Kiểu enum cho size
    private String productId; // Kiểu dữ liệu tương ứng với ID trong bảng Product
}
