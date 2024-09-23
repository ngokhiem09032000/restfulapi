package com.demoapi.democrud.mapper;

import com.demoapi.democrud.dto.request.UserCreationRequest;
import com.demoapi.democrud.dto.request.UserUpdateRequest;
import com.demoapi.democrud.dto.response.UserResponse;
import com.demoapi.democrud.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles" , ignore = true)
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    @Mapping(target = "roles" , ignore = true)
    @Mapping(target = "passWord" , ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
