package com.demoapi.democrud.controller;

import com.demoapi.democrud.dto.request.*;
import com.demoapi.democrud.dto.response.PermissionResponse;
import com.demoapi.democrud.dto.response.ProductResponse;
import com.demoapi.democrud.service.PermissionService;
import com.demoapi.democrud.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductController {

    ProductService productService;

    @PostMapping
    ApiResponse<ProductResponse> create(@RequestBody ProductCreateRequest request){
        return ApiResponse.<ProductResponse>builder()
                .code(1000)
                .result(productService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProductResponse>> getAll(){
        return ApiResponse.<List<ProductResponse>>builder()
                .code(1000)
                .result(productService.getAll())
                .build();
    }

    @GetMapping("/search")
    ApiResponse<Object> search(
            @RequestParam String keySearch,
            @RequestParam int page,
            @RequestParam int size) {

        return ApiResponse.builder()
                .code(1000)
                .result(productService.getProduct(keySearch,page,size))
                .build();
    }

    @GetMapping("/product-view")
    ApiResponse<Object> searchView(
            @RequestParam String keySearch,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam int stock,
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        return ApiResponse.builder()
                .code(1000)
                .result(productService.getProductView(keySearch,stock,minPrice,maxPrice,page,size))
                .build();
    }

    @DeleteMapping("/{product}")
    ApiResponse<Void> delete(@PathVariable String product){
        productService.delete(product);
        return ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }

    @PutMapping("/{product}")
    ApiResponse<ProductResponse> update(@PathVariable String product, @RequestBody ProductUpdateRequest request){
        return ApiResponse.<ProductResponse>builder()
                .code(1000)
                .result(productService.update(product,request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable String id) {
        ProductResponse product = productService.getProduct(id);
        return ApiResponse.<ProductResponse>builder()
                .code(1000)
                .result(product)
                .build();
    }
}
