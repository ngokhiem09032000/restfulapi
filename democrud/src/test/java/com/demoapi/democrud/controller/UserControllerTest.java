package com.demoapi.democrud.controller;

import com.demoapi.democrud.dto.request.UserCreationRequest;
import com.demoapi.democrud.dto.response.UserResponse;
import com.demoapi.democrud.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserCreationRequest request;
    private UserResponse userResponse;
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
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);

        Mockito.when(userService.createUser(ArgumentMatchers.any()))
                        .thenReturn(userResponse);

        // WHEN , THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1000"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("cgf121234123"));
    }

    @Test
    void createUser_usernameInvalid_fail() throws Exception {
        // GIVEN
        request.setUserName("kh");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);

        // WHEN , THEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content)
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1004"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Username must be at least 3 characters"));
    }

}
