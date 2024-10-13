package com.demoapi.democrud.dto.request;

import com.demoapi.democrud.entity.SizeId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SizeUpdateRequest {
    String value;
    String stock;
}
