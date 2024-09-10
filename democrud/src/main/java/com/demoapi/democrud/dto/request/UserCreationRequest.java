package com.demoapi.democrud.dto.request;

import com.demoapi.democrud.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3,message = "USERNAME_INVALID")
    String userName;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String passWord;
    String fullName;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate birthDate;

    List<String> roles;
}
