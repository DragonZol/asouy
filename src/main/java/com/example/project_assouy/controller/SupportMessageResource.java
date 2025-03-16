package com.example.project_assouy.controller;

import com.example.project_assouy.dto.SupportMessageDto;
import com.example.project_assouy.entity.SupportMessage;
import com.example.project_assouy.mapper.SupportMessageMapper;
import com.example.project_assouy.repo.SupportMessageRepository;
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
@RequestMapping("/crud/supportMessages")
@RequiredArgsConstructor
public class SupportMessageResource {

    private final SupportMessageRepository supportMessageRepository;

    private final SupportMessageMapper supportMessageMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<SupportMessageDto> getAll() {
        List<SupportMessage> supportMessages = supportMessageRepository.findAll();
        return supportMessages.stream()
                .map(supportMessageMapper::toSupportMessageDto)
                .toList();
    }

    @GetMapping("/{id}")
    public SupportMessageDto getOne(@PathVariable UUID id) {
        Optional<SupportMessage> supportMessageOptional = supportMessageRepository.findById(id);
        return supportMessageMapper.toSupportMessageDto(supportMessageOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<SupportMessageDto> getMany(@RequestParam List<UUID> ids) {
        List<SupportMessage> supportMessages = supportMessageRepository.findAllById(ids);
        return supportMessages.stream()
                .map(supportMessageMapper::toSupportMessageDto)
                .toList();
    }

    @PostMapping
    public SupportMessageDto create(@RequestBody @Valid SupportMessageDto dto) {
        SupportMessage supportMessage = supportMessageMapper.toEntity(dto);
        SupportMessage resultSupportMessage = supportMessageRepository.save(supportMessage);
        return supportMessageMapper.toSupportMessageDto(resultSupportMessage);
    }

    @PatchMapping("/{id}")
    public SupportMessageDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        SupportMessage supportMessage = supportMessageRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        SupportMessageDto supportMessageDto = supportMessageMapper.toSupportMessageDto(supportMessage);
        objectMapper.readerForUpdating(supportMessageDto).readValue(patchNode);
        supportMessageMapper.updateWithNull(supportMessageDto, supportMessage);

        SupportMessage resultSupportMessage = supportMessageRepository.save(supportMessage);
        return supportMessageMapper.toSupportMessageDto(resultSupportMessage);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam @Valid List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<SupportMessage> supportMessages = supportMessageRepository.findAllById(ids);

        for (SupportMessage supportMessage : supportMessages) {
            SupportMessageDto supportMessageDto = supportMessageMapper.toSupportMessageDto(supportMessage);
            objectMapper.readerForUpdating(supportMessageDto).readValue(patchNode);
            supportMessageMapper.updateWithNull(supportMessageDto, supportMessage);
        }

        List<SupportMessage> resultSupportMessages = supportMessageRepository.saveAll(supportMessages);
        return resultSupportMessages.stream()
                .map(SupportMessage::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public SupportMessageDto delete(@PathVariable UUID id) {
        SupportMessage supportMessage = supportMessageRepository.findById(id).orElse(null);
        if (supportMessage != null) {
            supportMessageRepository.delete(supportMessage);
        }
        return supportMessageMapper.toSupportMessageDto(supportMessage);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        supportMessageRepository.deleteAllById(ids);
    }
}
