package com.demoapi.democrud.dto.response;

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
public class ProductResponse {
    String id;
    String name;
    String description;
    String color;
    String material;
    BigDecimal price;

    String imageUrl;

    String imageUrl2;

    String imageUrl3;

    LocalDate createdAt;

    LocalDate updatedAt;

    List<SizeResponse> sizes;
}
