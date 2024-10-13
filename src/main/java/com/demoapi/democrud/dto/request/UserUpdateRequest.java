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
public class UserUpdateRequest {
    String passWord;
    @Size(min = 6, message = "FULL_NAME_INVALID")
    String fullName;

//    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate birthDate;
    List<String> roles;
}
