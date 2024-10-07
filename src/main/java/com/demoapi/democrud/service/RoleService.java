package com.demoapi.democrud.service;

import com.demoapi.democrud.dto.request.RoleRequest;
import com.demoapi.democrud.dto.request.RoleUpdateRequest;
import com.demoapi.democrud.dto.response.RoleResponse;
import com.demoapi.democrud.dto.response.UserResponse;
import com.demoapi.democrud.entity.Role;
import com.demoapi.democrud.entity.User;
import com.demoapi.democrud.exception.AppEXception;
import com.demoapi.democrud.exception.ErrorCode;
import com.demoapi.democrud.mapper.RoleMapper;
import com.demoapi.democrud.repository.PermissionRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public RoleResponse update(String roleId, RoleUpdateRequest request){
        Role role = roleRepository.findById(roleId)
                .orElseThrow( () -> new AppEXception(ErrorCode.ROLE_NOT_FOUND) );

        roleMapper.updateRole(role, request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll(){
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<RoleResponse> getRole(String keySearch, int page, int size) {

        Pageable pageable = PageRequest.of(page, size); // Tạo đối tượng Pageable

        return roleRepository.findByNameContainingOrDescriptionContaining(keySearch,keySearch, pageable)
                .map(roleMapper::toRoleResponse);
    }

    public void delete(String role){
        roleRepository.deleteById(role);
    }

    public List<String> getColumnNames() {
        return Role.getKeyNames();
    }
}
