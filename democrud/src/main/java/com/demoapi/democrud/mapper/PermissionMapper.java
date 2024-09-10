package com.demoapi.democrud.mapper;

import com.demoapi.democrud.dto.request.PermissionRequest;
import com.demoapi.democrud.dto.request.UserUpdateRequest;
import com.demoapi.democrud.dto.response.PermissionResponse;
import com.demoapi.democrud.entity.Permission;
import com.demoapi.democrud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission user);
}
