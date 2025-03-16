package com.example.project_assouy.controller;

import com.example.project_assouy.dto.UserDocumentDto;
import com.example.project_assouy.entity.UserDocument;
import com.example.project_assouy.mapper.UserDocumentMapper;
import com.example.project_assouy.repo.UserDocumentRepository;
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
@RequestMapping("/crud/userDocuments")
@RequiredArgsConstructor
public class UserDocumentResource {

    private final UserDocumentRepository userDocumentRepository;

    private final UserDocumentMapper userDocumentMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<UserDocumentDto> getAll() {
        List<UserDocument> userDocuments = userDocumentRepository.findAll();
        return userDocuments.stream()
                .map(userDocumentMapper::toUserDocumentDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDocumentDto getOne(@PathVariable UUID id) {
        Optional<UserDocument> userDocumentOptional = userDocumentRepository.findById(id);
        return userDocumentMapper.toUserDocumentDto(userDocumentOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<UserDocumentDto> getMany(@RequestParam List<UUID> ids) {
        List<UserDocument> userDocuments = userDocumentRepository.findAllById(ids);
        return userDocuments.stream()
                .map(userDocumentMapper::toUserDocumentDto)
                .toList();
    }

    @PostMapping
    public UserDocumentDto create(@RequestBody @Valid UserDocumentDto dto) {
        UserDocument userDocument = userDocumentMapper.toEntity(dto);
        UserDocument resultUserDocument = userDocumentRepository.save(userDocument);
        return userDocumentMapper.toUserDocumentDto(resultUserDocument);
    }

    @PatchMapping("/{id}")
    public UserDocumentDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        UserDocument userDocument = userDocumentRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        UserDocumentDto userDocumentDto = userDocumentMapper.toUserDocumentDto(userDocument);
        objectMapper.readerForUpdating(userDocumentDto).readValue(patchNode);
        userDocumentMapper.updateWithNull(userDocumentDto, userDocument);

        UserDocument resultUserDocument = userDocumentRepository.save(userDocument);
        return userDocumentMapper.toUserDocumentDto(resultUserDocument);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam @Valid List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<UserDocument> userDocuments = userDocumentRepository.findAllById(ids);

        for (UserDocument userDocument : userDocuments) {
            UserDocumentDto userDocumentDto = userDocumentMapper.toUserDocumentDto(userDocument);
            objectMapper.readerForUpdating(userDocumentDto).readValue(patchNode);
            userDocumentMapper.updateWithNull(userDocumentDto, userDocument);
        }

        List<UserDocument> resultUserDocuments = userDocumentRepository.saveAll(userDocuments);
        return resultUserDocuments.stream()
                .map(UserDocument::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public UserDocumentDto delete(@PathVariable UUID id) {
        UserDocument userDocument = userDocumentRepository.findById(id).orElse(null);
        if (userDocument != null) {
            userDocumentRepository.delete(userDocument);
        }
        return userDocumentMapper.toUserDocumentDto(userDocument);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        userDocumentRepository.deleteAllById(ids);
    }
}
