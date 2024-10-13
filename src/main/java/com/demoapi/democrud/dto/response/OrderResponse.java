package com.demoapi.democrud.dto.response;

import com.demoapi.democrud.entity.OrderDetail;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
public class OrderResponse {
    String id;

    String userName;

    String fullName;

    String address;

    String phone;

    BigDecimal shippingFee;

    BigDecimal total;

    int statusOrder = 0; // 0 chưa thực hiện , 1 đang giao , 2 đã giao thành công

    int statusPay = 0; // 0 đang chờ thanh toán , 1 Đã thanh toán

    List<OrderDetailReponse> orderDetails;

    LocalDate createdAt;

    LocalDate updatedAt;
}
