package com.demoapi.democrud.mapper;

import com.demoapi.democrud.dto.request.RoleRequest;
import com.demoapi.democrud.dto.response.RoleResponse;
import com.demoapi.democrud.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role user);
}
