package com.example.project_assouy.controller;

import com.example.project_assouy.dto.UserLoginDto;
import com.example.project_assouy.dto.UserPersonalDto;
import com.example.project_assouy.dto.UserRegistrationDto;
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
        UserPersonal registeredUser = userService.registerUser(userRegistrationDto);
        // Возвращаем зарегистрированного пользователя и HTTP статус 201 CREATED
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserPersonalDto> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        UserPersonalDto user = userService.loginUser(userLoginDto);
        // Возвращаем ответ с токеном и HTTP статус 200 OK
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        return new ResponseEntity<>("Пользователь отключен", HttpStatus.OK);
    }
}