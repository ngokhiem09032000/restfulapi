package com.demoapi.democrud.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailOrder {

    String id; // id product

    String name;

    BigDecimal price;

    String color;

    private com.demoapi.democrud.enums.Size size;

    int amount;

    BigDecimal totalPrice;

    String imageUrl;
}
