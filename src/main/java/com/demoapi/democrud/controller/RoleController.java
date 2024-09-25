package com.demoapi.democrud.controller;

import com.demoapi.democrud.dto.request.ApiResponse;
import com.demoapi.democrud.dto.request.RoleRequest;
import com.demoapi.democrud.dto.request.RoleUpdateRequest;
import com.demoapi.democrud.dto.request.UserUpdateRequest;
import com.demoapi.democrud.dto.response.RoleResponse;
import com.demoapi.democrud.dto.response.UserResponse;
import com.demoapi.democrud.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleController {

    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .code(1000)
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .code(1000)
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }

    @PutMapping("/{role}")
    ApiResponse<RoleResponse> update(@PathVariable String role, @RequestBody RoleUpdateRequest request){
        return ApiResponse.<RoleResponse>builder()
                .code(1000)
                .result(roleService.update(role,request))
                .build();
    }

    @GetMapping("/keys")
    ApiResponse<Object> getAllKeys() {
        return ApiResponse.builder()
                .code(1000)
                .result(roleService.getColumnNames())
                .build();
    }

}
