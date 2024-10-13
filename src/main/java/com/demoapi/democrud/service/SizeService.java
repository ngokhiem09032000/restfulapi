package com.demoapi.democrud.service;

import com.demoapi.democrud.dto.request.PermissionRequest;
import com.demoapi.democrud.dto.request.PermissionUpdateRequest;
import com.demoapi.democrud.dto.request.SizeRequest;
import com.demoapi.democrud.dto.request.SizeUpdateRequest;
import com.demoapi.democrud.dto.response.PermissionResponse;
import com.demoapi.democrud.dto.response.SizeResponse;
import com.demoapi.democrud.entity.Permission;
import com.demoapi.democrud.entity.Product;
import com.demoapi.democrud.entity.Size;
import com.demoapi.democrud.entity.SizeId;
import com.demoapi.democrud.exception.AppEXception;
import com.demoapi.democrud.exception.ErrorCode;
import com.demoapi.democrud.mapper.PermissionMapper;
import com.demoapi.democrud.mapper.SizeMapper;
import com.demoapi.democrud.repository.PermissionRepository;
import com.demoapi.democrud.repository.ProductRepository;
import com.demoapi.democrud.repository.SizeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SizeService {

    SizeRepository sizeRepository;
    SizeMapper sizeMapper;

    public SizeResponse create(SizeRequest request){
        Size size = sizeMapper.toSize(request);
        size = sizeRepository.save(size);
        return sizeMapper.toSizeResponse(size);
    }

    public List<SizeResponse> getSize(String productId) {
        return sizeRepository.findByProductId(productId).stream().map(sizeMapper::toSizeResponse).toList();
    }

    public void delete(SizeId id){
        sizeRepository.deleteById(id);
    }

    public SizeResponse update(SizeId sizeId, int stock,Product product) {
        Size size = sizeRepository.findById(sizeId)
                .orElseGet(() -> {
                    // Nếu không tìm thấy, tạo mới một đối tượng Size
                    Size newSize = new Size();
                    newSize.setId(sizeId);// Copy stock nếu cần
                    return newSize;
                });
        size.setStock(stock);
        size.setProduct(product);

        size = sizeRepository.save(size);
        return sizeMapper.toSizeResponse(size);
    }
}
