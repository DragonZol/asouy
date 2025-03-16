package com.example.project_assouy.controller;

import com.example.project_assouy.dto.SupportTicketDto;
import com.example.project_assouy.entity.SupportTicket;
import com.example.project_assouy.entity.SupportTicketMapper;
import com.example.project_assouy.repo.SupportTicketRepository;
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
@RequestMapping("/crud/supportTickets")
@RequiredArgsConstructor
public class SupportTicketResource {

    private final SupportTicketRepository supportTicketRepository;

    private final SupportTicketMapper supportTicketMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<SupportTicketDto> getAll() {
        List<SupportTicket> supportTickets = supportTicketRepository.findAll();
        return supportTickets.stream()
                .map(supportTicketMapper::toSupportTicketDto)
                .toList();
    }

    @GetMapping("/{id}")
    public SupportTicketDto getOne(@PathVariable UUID id) {
        Optional<SupportTicket> supportTicketOptional = supportTicketRepository.findById(id);
        return supportTicketMapper.toSupportTicketDto(supportTicketOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<SupportTicketDto> getMany(@RequestParam List<UUID> ids) {
        List<SupportTicket> supportTickets = supportTicketRepository.findAllById(ids);
        return supportTickets.stream()
                .map(supportTicketMapper::toSupportTicketDto)
                .toList();
    }

    @PostMapping
    public SupportTicketDto create(@RequestBody @Valid SupportTicketDto dto) {
        SupportTicket supportTicket = supportTicketMapper.toEntity(dto);
        SupportTicket resultSupportTicket = supportTicketRepository.save(supportTicket);
        return supportTicketMapper.toSupportTicketDto(resultSupportTicket);
    }

    @PatchMapping("/{id}")
    public SupportTicketDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        SupportTicket supportTicket = supportTicketRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        SupportTicketDto supportTicketDto = supportTicketMapper.toSupportTicketDto(supportTicket);
        objectMapper.readerForUpdating(supportTicketDto).readValue(patchNode);
        supportTicketMapper.updateWithNull(supportTicketDto, supportTicket);

        SupportTicket resultSupportTicket = supportTicketRepository.save(supportTicket);
        return supportTicketMapper.toSupportTicketDto(resultSupportTicket);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam @Valid List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<SupportTicket> supportTickets = supportTicketRepository.findAllById(ids);

        for (SupportTicket supportTicket : supportTickets) {
            SupportTicketDto supportTicketDto = supportTicketMapper.toSupportTicketDto(supportTicket);
            objectMapper.readerForUpdating(supportTicketDto).readValue(patchNode);
            supportTicketMapper.updateWithNull(supportTicketDto, supportTicket);
        }

        List<SupportTicket> resultSupportTickets = supportTicketRepository.saveAll(supportTickets);
        return resultSupportTickets.stream()
                .map(SupportTicket::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public SupportTicketDto delete(@PathVariable UUID id) {
        SupportTicket supportTicket = supportTicketRepository.findById(id).orElse(null);
        if (supportTicket != null) {
            supportTicketRepository.delete(supportTicket);
        }
        return supportTicketMapper.toSupportTicketDto(supportTicket);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        supportTicketRepository.deleteAllById(ids);
    }
}
