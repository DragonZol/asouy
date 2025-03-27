package com.example.project_assouy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

/**
 * DTO для данных аутентификации.
 */
@Value
public class UserLoginDto {
    @NotBlank(message = "Login не может быть пустым")
    @Size(max = 50, message = "Login не должен превышать 50 символов")
    String userIdLogin;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(max = 255, message = "Пароль не должен превышать 255 символов")
    String userPassword;
}
