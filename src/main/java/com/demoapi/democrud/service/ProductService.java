package com.demoapi.democrud.service;

import com.demoapi.democrud.dto.request.ProductCreateRequest;
import com.demoapi.democrud.dto.request.ProductUpdateRequest;
import com.demoapi.democrud.dto.request.RoleRequest;
import com.demoapi.democrud.dto.request.RoleUpdateRequest;
import com.demoapi.democrud.dto.response.ProductResponse;
import com.demoapi.democrud.dto.response.RoleResponse;
import com.demoapi.democrud.dto.response.UserResponse;
import com.demoapi.democrud.entity.Product;
import com.demoapi.democrud.entity.Role;
import com.demoapi.democrud.exception.AppEXception;
import com.demoapi.democrud.exception.ErrorCode;
import com.demoapi.democrud.mapper.ProductMapper;
import com.demoapi.democrud.mapper.RoleMapper;
import com.demoapi.democrud.repository.PermissionRepository;
import com.demoapi.democrud.repository.ProductRepository;
import com.demoapi.democrud.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductResponse create(ProductCreateRequest request){
        Product product = productMapper.toProduct(request);

        product = productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    public ProductResponse update(String id, ProductUpdateRequest request){
        Product product = productRepository.findById(id)
                .orElseThrow( () -> new AppEXception(ErrorCode.PRODUCT_NOT_FOUND) );

        productMapper.updateProduct(product, request);

        product = productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> getAll(){
        var products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<ProductResponse> getProduct(String keySearch, int page, int size) {

        Pageable pageable = PageRequest.of(page, size); // Tạo đối tượng Pageable

        return productRepository.findByNameContaining(keySearch,pageable)
                .map(productMapper::toProductResponse);
    }

    public Page<ProductResponse> getProductView(String keySearch,int stock,double minPrice, double maxPrice, int page, int size) {

        Pageable pageable = PageRequest.of(page, size); // Tạo đối tượng Pageable

        return productRepository.findByNameContainingAndTotalStockGreaterThanAndPriceBetween(keySearch,stock,minPrice,maxPrice,pageable)
                .map(productMapper::toProductResponse);
    }

    public void delete(String id){
        productRepository.deleteById(id);
    }

    public ProductResponse getProduct(String id){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppEXception(ErrorCode.PRODUCT_NOT_FOUND));

        // Sắp xếp sizes theo thứ tự S, M, L, XL, XXL
        product.getSizes().sort((size1, size2) -> {
            List<String> order = List.of("S", "M", "L", "XL", "XXL");
            return Integer.compare(order.indexOf(size1.getId().getName().name()), order.indexOf(size2.getId().getName().name()));
        });

        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> getProductsByIds(List<String> ids) {
        List<Product> products = productRepository.findAllById(ids);

        if (products.isEmpty()) {
            throw new AppEXception(ErrorCode.PRODUCT_NOT_FOUND);
        }

        // Convert List<Product> to List<ProductResponse> using your mapper
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

}
