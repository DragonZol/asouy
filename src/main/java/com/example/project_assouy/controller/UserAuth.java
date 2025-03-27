package com.example.project_assouy.controller;

import com.example.project_assouy.dto.UserLoginDto;
import com.example.project_assouy.dto.UserRegistrationDto;
import com.example.project_assouy.entity.User;
import com.example.project_assouy.entity.UserPersonal;
import com.example.project_assouy.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserAuth {
    private final UserService userService; // Сервис для работы с пользователями

    @PostMapping("/register")
    public ResponseEntity<UserPersonal> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {

        // Логика регистрации:
        // 1. Проверка уникальности логина/номера телефона/Email (если необходимо)
        // 2. Шифрование пароля
        // 3. Преобразование DTO в сущность User
        // 4. Сохранение пользователя в БД

        UserPersonal registeredUser = userService.registerUser(userRegistrationDto);

        // Возвращаем зарегистрированного пользователя и HTTP статус 201 CREATED
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserPersonal> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {


        // Логика аутентификации:
        // 1. Поиск пользователя по логину
        // 2. Проверка корректности введённого пароля
        // 3. Генерация токена, например JWT, при успешной аутентификации

        UserPersonal user = userService.loginUser(userIdLogin, userPassword);

        // Возвращаем ответ с токеном и HTTP статус 200 OK
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}