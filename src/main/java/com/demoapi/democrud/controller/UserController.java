package com.demoapi.democrud.controller;

import com.demoapi.democrud.dto.request.ApiResponse;
import com.demoapi.democrud.dto.request.UserCreationRequest;
import com.demoapi.democrud.dto.request.UserUpdateRequest;
import com.demoapi.democrud.dto.response.UserResponse;
import com.demoapi.democrud.entity.User;
import com.demoapi.democrud.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest creationRequest){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(creationRequest));

        return apiResponse;
    }

    @GetMapping
    ApiResponse<Object> getUsers(){

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.builder()
                .code(1000)
                .result(userService.getUser())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<Object> getUser(@PathVariable("userId") String userId){
        return ApiResponse.builder()
                .code(1000)
                .result(userService.getUser(userId))
                .build();
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.updateUser(userId,userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }

    @GetMapping("/myinfo")
    ApiResponse<Object> getMyInfo(){
        return ApiResponse.builder()
                .code(1000)
                .result(userService.getMyInfo())
                .build();
    }

    @GetMapping("/keys")
    ApiResponse<Object> getAllKeys() {
        return ApiResponse.builder()
                .code(1000)
                .result(userService.getColumnNames())
                .build();
    }
}
