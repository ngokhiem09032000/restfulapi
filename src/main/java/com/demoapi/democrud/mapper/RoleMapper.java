package com.demoapi.democrud.mapper;

import com.demoapi.democrud.dto.request.RoleRequest;
import com.demoapi.democrud.dto.request.RoleUpdateRequest;
import com.demoapi.democrud.dto.request.UserUpdateRequest;
import com.demoapi.democrud.dto.response.RoleResponse;
import com.demoapi.democrud.entity.Role;
import com.demoapi.democrud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role user);
    @Mapping(target = "permissions" , ignore = true)
    void updateRole(@MappingTarget Role role, RoleUpdateRequest request);
}
