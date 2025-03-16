package com.example.project_assouy.controller;

import com.example.project_assouy.dto.LoanApplicationDto;
import com.example.project_assouy.entity.LoanApplication;
import com.example.project_assouy.mapper.LoanApplicationMapper;
import com.example.project_assouy.repo.LoanApplicationRepository;
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
@RequestMapping("/crud/loanApplications")
@RequiredArgsConstructor
public class LoanApplicationResource {

    private final LoanApplicationRepository loanApplicationRepository;

    private final LoanApplicationMapper loanApplicationMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<LoanApplicationDto> getAll() {
        List<LoanApplication> loanApplications = loanApplicationRepository.findAll();
        return loanApplications.stream()
                .map(loanApplicationMapper::toLoanApplicationDto)
                .toList();
    }

    @GetMapping("/{id}")
    public LoanApplicationDto getOne(@PathVariable UUID id) {
        Optional<LoanApplication> loanApplicationOptional = loanApplicationRepository.findById(id);
        return loanApplicationMapper.toLoanApplicationDto(loanApplicationOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<LoanApplicationDto> getMany(@RequestParam List<UUID> ids) {
        List<LoanApplication> loanApplications = loanApplicationRepository.findAllById(ids);
        return loanApplications.stream()
                .map(loanApplicationMapper::toLoanApplicationDto)
                .toList();
    }

    @PostMapping
    public LoanApplicationDto create(@RequestBody @Valid LoanApplicationDto dto) {
        LoanApplication loanApplication = loanApplicationMapper.toEntity(dto);
        LoanApplication resultLoanApplication = loanApplicationRepository.save(loanApplication);
        return loanApplicationMapper.toLoanApplicationDto(resultLoanApplication);
    }

    @PatchMapping("/{id}")
    public LoanApplicationDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        LoanApplication loanApplication = loanApplicationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        LoanApplicationDto loanApplicationDto = loanApplicationMapper.toLoanApplicationDto(loanApplication);
        objectMapper.readerForUpdating(loanApplicationDto).readValue(patchNode);
        loanApplicationMapper.updateWithNull(loanApplicationDto, loanApplication);

        LoanApplication resultLoanApplication = loanApplicationRepository.save(loanApplication);
        return loanApplicationMapper.toLoanApplicationDto(resultLoanApplication);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam @Valid List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<LoanApplication> loanApplications = loanApplicationRepository.findAllById(ids);

        for (LoanApplication loanApplication : loanApplications) {
            LoanApplicationDto loanApplicationDto = loanApplicationMapper.toLoanApplicationDto(loanApplication);
            objectMapper.readerForUpdating(loanApplicationDto).readValue(patchNode);
            loanApplicationMapper.updateWithNull(loanApplicationDto, loanApplication);
        }

        List<LoanApplication> resultLoanApplications = loanApplicationRepository.saveAll(loanApplications);
        return resultLoanApplications.stream()
                .map(LoanApplication::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public LoanApplicationDto delete(@PathVariable UUID id) {
        LoanApplication loanApplication = loanApplicationRepository.findById(id).orElse(null);
        if (loanApplication != null) {
            loanApplicationRepository.delete(loanApplication);
        }
        return loanApplicationMapper.toLoanApplicationDto(loanApplication);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        loanApplicationRepository.deleteAllById(ids);
    }
}
