package com.example.project_assouy.dto;

import com.example.project_assouy.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.time.LocalDate;

/**
 * DTO for {@link com.example.project_assouy.entity.UserPersonal}
 */
@Value
public class UserPersonalDto {
    User userId;
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