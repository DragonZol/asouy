package com.example.project_assouy.dto;

import com.example.project_assouy.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.time.LocalDate;

/**
 * DTO for {@link com.example.project_assouy.entity.UserPersonal}
 */
@Value
public class UserRegistrationDto {
    @NotNull
    @Size(max = 15)
    String userIdPhoneNumber;
    @NotNull
    @Size(max = 50)
    String userIdLogin;
    @NotNull
    @Size(max = 255)
    String userIdPasswordHash;
    Role userIdRole;
    @NotNull
    @Size(max = 100)
    String fullName;
    @NotNull
    LocalDate birthDate;
    @Size(max = 255)
    String email;
    @NotNull
    @Size(max = 50)
    String region;
}