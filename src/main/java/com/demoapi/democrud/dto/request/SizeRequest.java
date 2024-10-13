package com.demoapi.democrud.dto.request;

import com.demoapi.democrud.entity.SizeId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SizeRequest {
    private SizeId id;
    int stock;
}
