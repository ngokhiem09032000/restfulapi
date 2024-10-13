package com.demoapi.democrud.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InfoOrder {
    String userName;
    String fullName;
    String address;
    String phone;
    BigDecimal shippingFee;
    BigDecimal total;
    int statusOrder;
    int statusPay;
}
