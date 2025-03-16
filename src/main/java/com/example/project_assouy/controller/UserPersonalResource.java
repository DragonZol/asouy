package com.example.project_assouy.controller;

import com.example.project_assouy.dto.UserPersonalDto;
import com.example.project_assouy.entity.UserPersonal;
import com.example.project_assouy.mapper.UserPersonalMapper;
import com.example.project_assouy.repo.UserPersonalRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/crud/userPersonals")
@RequiredArgsConstructor
public class UserPersonalResource {

    private final UserPersonalRepository userPersonalRepository;

    private final UserPersonalMapper userPersonalMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<UserPersonalDto> getAll() {
        List<UserPersonal> userPersonals = userPersonalRepository.findAll();
        return userPersonals.stream()
                .map(userPersonalMapper::toUserPersonalDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserPersonalDto getOne(@PathVariable UUID id) {
        Optional<UserPersonal> userPersonalOptional = userPersonalRepository.findById(id);
        return userPersonalMapper.toUserPersonalDto(userPersonalOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<UserPersonalDto> getMany(@RequestParam List<UUID> ids) {
        List<UserPersonal> userPersonals = userPersonalRepository.findAllById(ids);
        return userPersonals.stream()
                .map(userPersonalMapper::toUserPersonalDto)
                .toList();
    }

    @PostMapping
    public UserPersonalDto create(@RequestBody @Valid UserPersonalDto dto) {
        UserPersonal userPersonal = userPersonalMapper.toEntity(dto);
        UserPersonal resultUserPersonal = userPersonalRepository.save(userPersonal);
        return userPersonalMapper.toUserPersonalDto(resultUserPersonal);
    }

    @PatchMapping("/{id}")
    public UserPersonalDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        UserPersonal userPersonal = userPersonalRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        UserPersonalDto userPersonalDto = userPersonalMapper.toUserPersonalDto(userPersonal);
        objectMapper.readerForUpdating(userPersonalDto).readValue(patchNode);
        userPersonalMapper.updateWithNull(userPersonalDto, userPersonal);

        UserPersonal resultUserPersonal = userPersonalRepository.save(userPersonal);
        return userPersonalMapper.toUserPersonalDto(resultUserPersonal);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam @Valid List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<UserPersonal> userPersonals = userPersonalRepository.findAllById(ids);

        for (UserPersonal userPersonal : userPersonals) {
            UserPersonalDto userPersonalDto = userPersonalMapper.toUserPersonalDto(userPersonal);
            objectMapper.readerForUpdating(userPersonalDto).readValue(patchNode);
            userPersonalMapper.updateWithNull(userPersonalDto, userPersonal);
        }

        List<UserPersonal> resultUserPersonals = userPersonalRepository.saveAll(userPersonals);
        return resultUserPersonals.stream()
                .map(UserPersonal::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public UserPersonalDto delete(@PathVariable UUID id) {
        UserPersonal userPersonal = userPersonalRepository.findById(id).orElse(null);
        if (userPersonal != null) {
            userPersonalRepository.delete(userPersonal);
        }
        return userPersonalMapper.toUserPersonalDto(userPersonal);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        userPersonalRepository.deleteAllById(ids);
    }
}
