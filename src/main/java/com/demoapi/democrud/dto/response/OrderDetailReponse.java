package com.demoapi.democrud.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailReponse {

    String id;

    String productId;

    String name;

    BigDecimal price;

    String color;

    private com.demoapi.democrud.enums.Size size;

    int amount;

    BigDecimal totalPrice;

    String imageUrl;
}
