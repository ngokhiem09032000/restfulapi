package com.demoapi.democrud.mapper;

import com.demoapi.democrud.dto.request.ProductCreateRequest;
import com.demoapi.democrud.dto.request.ProductUpdateRequest;
import com.demoapi.democrud.dto.response.ProductResponse;
import com.demoapi.democrud.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreateRequest request);
    ProductResponse toProductResponse(Product user);
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
