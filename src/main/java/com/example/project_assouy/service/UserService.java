package com.example.project_assouy.service;

import com.example.project_assouy.dto.UserLoginDto;
import com.example.project_assouy.dto.UserPersonalDto;
import com.example.project_assouy.dto.UserRegistrationDto;

import com.example.project_assouy.entity.User;
import com.example.project_assouy.entity.UserPersonal;
import com.example.project_assouy.mapper.UserPersonalMapper;
import com.example.project_assouy.repo.UserPersonalRepository;
import com.example.project_assouy.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserPersonalRepository userPersonalRepository;
    private final UserPersonalMapper userPersonalMapper;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public UserPersonal registerUser(UserRegistrationDto dto) {
        // Проверка существования пользователя по логину
        if (userRepository.existsByLogin(dto.getUserIdLogin())) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }


        User user = new User();
        user.setCreateTs(LocalDateTime.now());
        user.setCreatedBy("admin");
        user.setIsActive(true);
        user.setPhoneNumber(dto.getUserIdPhoneNumber());
        user.setLogin(dto.getUserIdLogin());
        user.setPasswordHash(passwordEncoder.encode(dto.getUserIdPasswordHash()));
        user.setRole(dto.getUserIdRole());

        User savedUser = userRepository.save(user);

        UserPersonal userPersonal = new UserPersonal();
        userPersonal.setUserId(savedUser);
        userPersonal.setFullName(dto.getFullName());
        userPersonal.setBirthDate(dto.getBirthDate());
        userPersonal.setEmail(dto.getEmail());
        userPersonal.setRegion(dto.getRegion());
        userPersonal.setCreateTs(LocalDateTime.now());
        userPersonal.setCreatedBy("admin");

        return userPersonalRepository.save(userPersonal);
    }

    public UserPersonalDto loginUser(UserLoginDto dto) {
        // Проверяем, существует ли пользователь с указанным логином
        if (!userRepository.existsUserByLogin(dto.getLogin())) {
            throw new RuntimeException("Пользователь с таким логином не существует");
        }

        // Получаем пользователя по логину
        User user = userRepository.findUserByLogin(dto.getLogin());

        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Неверный пароль");
        }

        UserPersonal userPersonal = userPersonalRepository.findIdByUserId(user);

        // Получаем дополнительные персональные данные пользователя
        Optional<UserPersonal> userPersonalOptional = userPersonalRepository.findById(userPersonal.getId());
        return userPersonalMapper.toUserPersonalDto(userPersonalOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(userPersonal.getId()))));
    }
}
