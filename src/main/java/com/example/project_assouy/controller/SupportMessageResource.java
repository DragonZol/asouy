package com.example.project_assouy.controller;

import com.example.project_assouy.dto.SupportMessageDto;
import com.example.project_assouy.entity.SupportMessage;
import com.example.project_assouy.entity.SupportTicket;
import com.example.project_assouy.mapper.SupportMessageMapper;
import com.example.project_assouy.repo.SupportMessageRepository;
import com.example.project_assouy.repo.SupportTicketRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/supportMessages/tickets")
@RequiredArgsConstructor
public class SupportMessageResource {

    private final SupportMessageRepository supportMessageRepository;

    private final SupportMessageMapper supportMessageMapper;

    private final ObjectMapper objectMapper;
    private final SupportTicketRepository supportTicketRepository;

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
    @GetMapping("/{id}/messages")
    public List<SupportMessageDto> getMessagesByTicket(@PathVariable UUID id) {
        // Проверяем наличие тикета
        SupportTicket ticket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ticket with id " + id + " not found"));

        // Загружаем все сообщения тикета по id тикета
        List<SupportMessage> messages = supportMessageRepository.findSupportMessagesByTicket_Id(ticket.getId());

        // Преобразуем список сообщений в список DTO и возвращаем
        return messages.stream()
                .map(supportMessageMapper::toSupportMessageDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public SupportMessageDto create(@RequestBody @Valid SupportMessageDto dto) {
        SupportMessage supportMessage = supportMessageMapper.toEntity(dto);
        SupportMessage resultSupportMessage = supportMessageRepository.save(supportMessage);
        return supportMessageMapper.toSupportMessageDto(resultSupportMessage);
    }

    @PostMapping("/{id}/messages")
    public SupportMessageDto createmessage(@PathVariable UUID id,@RequestBody SupportMessageDto dto) {
        // Загрузка тикета по id
        SupportTicket ticket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ticket with id " + id + " not found"));

        // Создание нового сообщения
        SupportMessage supportMessage = new SupportMessage();
        supportMessage.setTicket(ticket);
        supportMessage.setCreateTs(LocalDateTime.now());
        supportMessage.setCreatedBy("admin");
        supportMessage.setMessage(dto.getMessage());

        // При необходимости установите другие поля (например, автора, дату и т.д.)

        // Сохранение сообщения
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
