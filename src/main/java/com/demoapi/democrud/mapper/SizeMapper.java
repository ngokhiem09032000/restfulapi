package com.demoapi.democrud.mapper;

import com.demoapi.democrud.dto.request.SizeRequest;
import com.demoapi.democrud.dto.request.SizeUpdateRequest;
import com.demoapi.democrud.dto.response.SizeResponse;
import com.demoapi.democrud.entity.Size;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SizeMapper {
    Size toSize(SizeRequest request);
    SizeResponse toSizeResponse(Size size);
    void updateSize(@MappingTarget Size size, SizeUpdateRequest request);
}
