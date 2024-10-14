package com.demoapi.democrud.controller;

import com.demoapi.democrud.dto.request.ApiResponse;
import com.demoapi.democrud.dto.request.OrderRequest;
import com.demoapi.democrud.dto.request.ProductUpdateRequest;
import com.demoapi.democrud.dto.response.OrderResponse;
import com.demoapi.democrud.dto.response.ProductResponse;
import com.demoapi.democrud.entity.Order;
import com.demoapi.democrud.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderController {

    OrderService orderService;

    @PostMapping
    ApiResponse<String> create(@RequestBody OrderRequest request){
        orderService.create(request);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("Save order success.")
                .build();
    }

    @GetMapping("/{userName}")
    ApiResponse<List<Order>> getOrder(@PathVariable("userName") String userName){
        return ApiResponse.<List<Order>>builder()
                .code(1000)
                .result(orderService.getOrderByUserName(userName))
                .build();
    }
}
