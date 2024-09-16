package com.demoapi.democrud.service;

import com.demoapi.democrud.dto.request.UserCreationRequest;
import com.demoapi.democrud.dto.response.UserResponse;
import com.demoapi.democrud.entity.User;
import com.demoapi.democrud.exception.AppEXception;
import com.demoapi.democrud.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData(){

        dob = LocalDate.of(1990,1,1);

        request = UserCreationRequest.builder()
                .userName("khiem")
                .fullName("ngo khiem")
                .passWord("12345678")
                .birthDate(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("cgf121234123")
                .userName("khiem")
                .fullName("ngo khiem")
                .birthDate(dob)
                .build();

        user = User.builder()
                .id("cgf121234123")
                .userName("khiem")
                .fullName("ngo khiem")
                .birthDate(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success(){
        // GIVEN

        Mockito.when(userRepository.existsByUserName(anyString())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        // When
        var response = userService.createUser(request);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("cgf121234123");
        Assertions.assertThat(response.getUserName()).isEqualTo("khiem");
    }

    @Test
    void createUser_userExisted_fail(){
        // GIVEN

        Mockito.when(userRepository.existsByUserName(anyString())).thenReturn(true);

        // When
        var exception = assertThrows(AppEXception.class, () ->userService.createUser(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }

    @Test
    @WithMockUser(username = "khiem")
    void getMyInfo_valid_success(){
        Mockito.when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));

        var response = userService.getMyInfo();

        Assertions.assertThat(response.getUserName()).isEqualTo("khiem");
        Assertions.assertThat(response.getId()).isEqualTo("cgf121234123");
    }

    @Test
    @WithMockUser(username = "khiem")
    void getMyInfo_userNotFound_error(){
        Mockito.when(userRepository.findByUserName(anyString())).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(AppEXception.class, () ->userService.getMyInfo());

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1006);
    }
}
