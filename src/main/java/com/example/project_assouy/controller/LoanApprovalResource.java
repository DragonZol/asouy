package com.example.project_assouy.controller;

import com.example.project_assouy.dto.LoanApprovalDto;
import com.example.project_assouy.entity.LoanApproval;
import com.example.project_assouy.mapper.LoanApprovalMapper;
import com.example.project_assouy.repo.LoanApprovalRepository;
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
@RequestMapping("/crud/loanApprovals")
@RequiredArgsConstructor
public class LoanApprovalResource {

    private final LoanApprovalRepository loanApprovalRepository;

    private final LoanApprovalMapper loanApprovalMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<LoanApprovalDto> getAll() {
        List<LoanApproval> loanApprovals = loanApprovalRepository.findAll();
        return loanApprovals.stream()
                .map(loanApprovalMapper::toLoanApprovalDto)
                .toList();
    }

    @GetMapping("/{id}")
    public LoanApprovalDto getOne(@PathVariable UUID id) {
        Optional<LoanApproval> loanApprovalOptional = loanApprovalRepository.findById(id);
        return loanApprovalMapper.toLoanApprovalDto(loanApprovalOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<LoanApprovalDto> getMany(@RequestParam List<UUID> ids) {
        List<LoanApproval> loanApprovals = loanApprovalRepository.findAllById(ids);
        return loanApprovals.stream()
                .map(loanApprovalMapper::toLoanApprovalDto)
                .toList();
    }

    @PostMapping
    public LoanApprovalDto create(@RequestBody @Valid LoanApprovalDto dto) {
        LoanApproval loanApproval = loanApprovalMapper.toEntity(dto);
        LoanApproval resultLoanApproval = loanApprovalRepository.save(loanApproval);
        return loanApprovalMapper.toLoanApprovalDto(resultLoanApproval);
    }

    @PatchMapping("/{id}")
    public LoanApprovalDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        LoanApproval loanApproval = loanApprovalRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        LoanApprovalDto loanApprovalDto = loanApprovalMapper.toLoanApprovalDto(loanApproval);
        objectMapper.readerForUpdating(loanApprovalDto).readValue(patchNode);
        loanApprovalMapper.updateWithNull(loanApprovalDto, loanApproval);

        LoanApproval resultLoanApproval = loanApprovalRepository.save(loanApproval);
        return loanApprovalMapper.toLoanApprovalDto(resultLoanApproval);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<LoanApproval> loanApprovals = loanApprovalRepository.findAllById(ids);

        for (LoanApproval loanApproval : loanApprovals) {
            LoanApprovalDto loanApprovalDto = loanApprovalMapper.toLoanApprovalDto(loanApproval);
            objectMapper.readerForUpdating(loanApprovalDto).readValue(patchNode);
            loanApprovalMapper.updateWithNull(loanApprovalDto, loanApproval);
        }

        List<LoanApproval> resultLoanApprovals = loanApprovalRepository.saveAll(loanApprovals);
        return resultLoanApprovals.stream()
                .map(LoanApproval::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public LoanApprovalDto delete(@PathVariable UUID id) {
        LoanApproval loanApproval = loanApprovalRepository.findById(id).orElse(null);
        if (loanApproval != null) {
            loanApprovalRepository.delete(loanApproval);
        }
        return loanApprovalMapper.toLoanApprovalDto(loanApproval);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        loanApprovalRepository.deleteAllById(ids);
    }
}
