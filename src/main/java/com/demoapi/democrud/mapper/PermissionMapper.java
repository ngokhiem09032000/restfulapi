package com.demoapi.democrud.mapper;

import com.demoapi.democrud.dto.request.PermissionRequest;
import com.demoapi.democrud.dto.request.PermissionUpdateRequest;
import com.demoapi.democrud.dto.response.PermissionResponse;
import com.demoapi.democrud.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
    void updatePermission(@MappingTarget Permission permission, PermissionUpdateRequest request);
}
