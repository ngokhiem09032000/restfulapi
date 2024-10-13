package com.demoapi.democrud.controller;

import com.demoapi.democrud.dto.request.*;
import com.demoapi.democrud.dto.response.PermissionResponse;
import com.demoapi.democrud.dto.response.SizeResponse;
import com.demoapi.democrud.entity.Product;
import com.demoapi.democrud.entity.Size;
import com.demoapi.democrud.entity.SizeId;
import com.demoapi.democrud.exception.AppEXception;
import com.demoapi.democrud.exception.ErrorCode;
import com.demoapi.democrud.repository.ProductRepository;
import com.demoapi.democrud.service.PermissionService;
import com.demoapi.democrud.service.ProductService;
import com.demoapi.democrud.service.SizeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sizes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SizeController {

    SizeService sizeService;
    ProductRepository productRepository;

    @PostMapping
    ApiResponse<SizeResponse> create(@RequestBody SizeRequest request){
        return ApiResponse.<SizeResponse>builder()
                .code(1000)
                .result(sizeService.create(request))
                .build();
    }

    @GetMapping("/{productId}")
    ApiResponse<List<SizeResponse>> getSizeByProductId(@PathVariable String productId){
        return ApiResponse.<List<SizeResponse>>builder()
                .code(1000)
                .result(sizeService.getSize(productId))
                .build();
    }

    @DeleteMapping("/size")
    ApiResponse<Void> delete(@RequestParam String productId, @RequestParam String name){
        SizeId sizeIdObj = new SizeId();
        sizeIdObj.setName(com.demoapi.democrud.enums.Size.valueOf(name));
        sizeIdObj.setProductId(productId);
        sizeService.delete(sizeIdObj);
        return ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }

//    @PutMapping("/size")
//    ApiResponse<SizeResponse> update(@RequestParam String productId, @RequestParam String name, @RequestBody SizeUpdateRequest request){
//        SizeId sizeIdObj = new SizeId();
//        sizeIdObj.setName(com.demoapi.democrud.enums.Size.valueOf(name));
//        sizeIdObj.setProductId(productId);
//        return ApiResponse.<SizeResponse>builder()
//                .code(1000)
//                .result(sizeService.update(sizeIdObj,request))
//                .build();
//    }

    @PutMapping("/{productId}")
    ApiResponse<String> update(@PathVariable("productId") String productId, @RequestBody List<SizeUpdateRequest> sizeUpdateRequests){
        Product product = productRepository.findById(productId)
                .orElseThrow( () -> new AppEXception(ErrorCode.PRODUCT_NOT_FOUND));

        List<Size> sizes = new ArrayList<>();

        for (SizeUpdateRequest sizeUpdateRequest : sizeUpdateRequests) {
            SizeId sizeIdObj = new SizeId();
            sizeIdObj.setName(com.demoapi.democrud.enums.Size.valueOf(sizeUpdateRequest.getValue()));
            sizeIdObj.setProductId(productId);
            int stock = Optional.ofNullable(sizeUpdateRequest.getStock())
                    .map(Integer::parseInt)
                    .orElse(0);
            sizes.add(Size.builder()
                    .id(sizeIdObj)
                    .stock(stock)
                    .product(product)
                    .build());
        }
        product.setSizes(sizes);
        productRepository.save(product);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("Save sizes success")
                .build();
    }

}
