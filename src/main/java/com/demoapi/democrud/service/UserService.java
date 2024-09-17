package com.demoapi.democrud.service;

import com.demoapi.democrud.dto.request.UserCreationRequest;
import com.demoapi.democrud.dto.request.UserUpdateRequest;
import com.demoapi.democrud.dto.response.UserResponse;
import com.demoapi.democrud.entity.User;
import com.demoapi.democrud.enums.Role;
import com.demoapi.democrud.exception.AppEXception;
import com.demoapi.democrud.exception.ErrorCode;
import com.demoapi.democrud.mapper.UserMapper;
import com.demoapi.democrud.repository.RoleRepository;
import com.demoapi.democrud.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest creationRequest){

        User user = userMapper.toUser(creationRequest);

        user.setPassWord(passwordEncoder.encode(creationRequest.getPassWord()));

        if(!Objects.isNull(creationRequest.getRoles())){
            var roles = roleRepository.findAllById(creationRequest.getRoles());
            user.setRoles(new HashSet<>(roles));
        }

        try {
            user = userRepository.save(user);
        }catch (DataIntegrityViolationException exception){
            throw  new AppEXception(ErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUser(){
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.userName == authentication.name")
    public UserResponse getUser(String id){
        log.info("In method get user by Id");
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow( () -> new AppEXception(ErrorCode.USER_NOT_FOUND) ));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new AppEXception(ErrorCode.USER_NOT_FOUND) );

        userMapper.updateUser(user,userUpdateRequest);
        user.setPassWord(passwordEncoder.encode(userUpdateRequest.getPassWord()));

        var roles = roleRepository.findAllById(userUpdateRequest.getRoles());
        user.setRoles(new HashSet<>(roles));

        return  userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUserName(name).orElseThrow(
                () -> new AppEXception(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }
}
