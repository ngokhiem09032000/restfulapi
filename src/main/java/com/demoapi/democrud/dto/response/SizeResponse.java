package com.demoapi.democrud.dto.response;

import com.demoapi.democrud.entity.Product;
import com.demoapi.democrud.entity.SizeId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SizeResponse {
    private SizeId id;
    int stock;
}
