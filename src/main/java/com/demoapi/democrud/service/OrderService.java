package com.demoapi.democrud.service;

import com.demoapi.democrud.dto.request.DetailOrder;
import com.demoapi.democrud.dto.request.OrderRequest;
import com.demoapi.democrud.dto.request.ProductCreateRequest;
import com.demoapi.democrud.dto.request.ProductUpdateRequest;
import com.demoapi.democrud.dto.response.ProductResponse;
import com.demoapi.democrud.entity.Order;
import com.demoapi.democrud.entity.OrderDetail;
import com.demoapi.democrud.entity.Product;
import com.demoapi.democrud.exception.AppEXception;
import com.demoapi.democrud.exception.ErrorCode;
import com.demoapi.democrud.mapper.ProductMapper;
import com.demoapi.democrud.repository.OrderRepository;
import com.demoapi.democrud.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;

    public Order create(OrderRequest request){

        try {
            Order order = Order.builder()
                    .userName(request.getInfo().getUserName())
                    .fullName(request.getInfo().getFullName())
                    .address(request.getInfo().getAddress())
                    .phone(request.getInfo().getPhone())
                    .shippingFee(request.getInfo().getShippingFee())
                    .total(request.getInfo().getTotal())
                    .statusOrder(request.getInfo().getStatusOrder())
                    .statusPay(request.getInfo().getStatusPay())
                    .build();

            List<OrderDetail> orderDetails = new ArrayList<>();

            for(int i = 0 ; i < request.getDetailOrders().size(); i++){
                DetailOrder detailOrder = request.getDetailOrders().get(i);
                OrderDetail orderDetail = OrderDetail.builder()
                        .productId(detailOrder.getId())
                        .name(detailOrder.getName())
                        .price(detailOrder.getPrice())
                        .color(detailOrder.getColor())
                        .size(detailOrder.getSize())
                        .amount(detailOrder.getAmount())
                        .totalPrice(detailOrder.getTotalPrice())
                        .imageUrl(detailOrder.getImageUrl())
                        .order(order)
                        .build();
                orderDetails.add(orderDetail);
            }

            order.setOrderDetails(orderDetails);

            order = orderRepository.save(order);
            return order;
        }catch (Exception e){
            throw  new AppEXception(ErrorCode.SAVE_ORDER_FAIL);
        }

    }

}
