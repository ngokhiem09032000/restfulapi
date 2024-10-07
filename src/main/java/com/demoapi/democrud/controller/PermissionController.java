package com.demoapi.democrud.controller;

import com.demoapi.democrud.dto.request.ApiResponse;
import com.demoapi.democrud.dto.request.PermissionRequest;
import com.demoapi.democrud.dto.request.PermissionUpdateRequest;
import com.demoapi.democrud.dto.response.PermissionResponse;
import com.demoapi.democrud.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .code(1000)
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(1000)
                .result(permissionService.getAll())
                .build();
    }

    @GetMapping("/search")
    ApiResponse<Object> searchPermissions(
            @RequestParam String keySearch,
            @RequestParam int page,
            @RequestParam int size) {

        return ApiResponse.builder()
                .code(1000)
                .result(permissionService.getPermission(keySearch,page,size))
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission){
        permissionService.delete(permission);
        return ApiResponse.<Void>builder()
                .code(1000)
                .build();
    }

    @PutMapping("/{permission}")
    ApiResponse<PermissionResponse> update(@PathVariable String permission, @RequestBody PermissionUpdateRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .code(1000)
                .result(permissionService.update(permission,request))
                .build();
    }

    @GetMapping("/keys")
    ApiResponse<Object> getAllKeys() {
        return ApiResponse.builder()
                .code(1000)
                .result(permissionService.getColumnNames())
                .build();
    }
}
