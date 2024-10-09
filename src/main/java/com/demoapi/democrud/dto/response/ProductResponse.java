package com.demoapi.democrud.dto.response;

import com.demoapi.democrud.entity.Product;
import com.demoapi.democrud.enums.Size;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String name;
    String description;
    Size size;
    String color;
    String material;
    BigDecimal price;
    int stock = 0; // Số lượng tồn kho

    String imageUrl;

    String imageUrl2;

    String imageUrl3;

    LocalDate createdAt;

    LocalDate updatedAt;
}
