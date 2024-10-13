package com.demoapi.democrud.dto.request;

import com.demoapi.democrud.enums.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {
    String name;
    String description;
    String color;
    String material;
    BigDecimal price;

    String imageUrl;

    String imageUrl2;

    String imageUrl3;
}
