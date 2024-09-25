package com.demoapi.democrud.service;

import com.demoapi.democrud.dto.request.PermissionRequest;
import com.demoapi.democrud.dto.request.PermissionUpdateRequest;
import com.demoapi.democrud.dto.response.PermissionResponse;
import com.demoapi.democrud.dto.response.RoleResponse;
import com.demoapi.democrud.entity.Permission;
import com.demoapi.democrud.exception.AppEXception;
import com.demoapi.democrud.exception.ErrorCode;
import com.demoapi.democrud.mapper.PermissionMapper;
import com.demoapi.democrud.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }

    public PermissionResponse update(String permissionId, PermissionUpdateRequest request){
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow( () -> new AppEXception(ErrorCode.ROLE_NOT_FOUND) );

        permissionMapper.updatePermission(permission, request);

        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<String> getColumnNames() {
        return Permission.getKeyNames();
    }
}
