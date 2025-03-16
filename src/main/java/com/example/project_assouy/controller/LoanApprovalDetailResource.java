package com.example.project_assouy.controller;

import com.example.project_assouy.dto.LoanApprovalDetailDto;
import com.example.project_assouy.entity.LoanApprovalDetail;
import com.example.project_assouy.mapper.LoanApprovalDetailMapper;
import com.example.project_assouy.repo.LoanApprovalDetailRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("/crud/loanApprovalDetails")
@RequiredArgsConstructor
public class LoanApprovalDetailResource {

    private final LoanApprovalDetailRepository loanApprovalDetailRepository;

    private final LoanApprovalDetailMapper loanApprovalDetailMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<LoanApprovalDetailDto> getAll() {
        List<LoanApprovalDetail> loanApprovalDetails = loanApprovalDetailRepository.findAll();
        return loanApprovalDetails.stream()
                .map(loanApprovalDetailMapper::toLoanApprovalDetailDto)
                .toList();
    }

    @GetMapping("/{id}")
    public LoanApprovalDetailDto getOne(@PathVariable UUID id) {
        Optional<LoanApprovalDetail> loanApprovalDetailOptional = loanApprovalDetailRepository.findById(id);
        return loanApprovalDetailMapper.toLoanApprovalDetailDto(loanApprovalDetailOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<LoanApprovalDetailDto> getMany(@RequestParam List<UUID> ids) {
        List<LoanApprovalDetail> loanApprovalDetails = loanApprovalDetailRepository.findAllById(ids);
        return loanApprovalDetails.stream()
                .map(loanApprovalDetailMapper::toLoanApprovalDetailDto)
                .toList();
    }

    @PostMapping
    public LoanApprovalDetailDto create(@RequestBody LoanApprovalDetailDto dto) {
        LoanApprovalDetail loanApprovalDetail = loanApprovalDetailMapper.toEntity(dto);
        LoanApprovalDetail resultLoanApprovalDetail = loanApprovalDetailRepository.save(loanApprovalDetail);
        return loanApprovalDetailMapper.toLoanApprovalDetailDto(resultLoanApprovalDetail);
    }

    @PatchMapping("/{id}")
    public LoanApprovalDetailDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        LoanApprovalDetail loanApprovalDetail = loanApprovalDetailRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        LoanApprovalDetailDto loanApprovalDetailDto = loanApprovalDetailMapper.toLoanApprovalDetailDto(loanApprovalDetail);
        objectMapper.readerForUpdating(loanApprovalDetailDto).readValue(patchNode);
        loanApprovalDetailMapper.updateWithNull(loanApprovalDetailDto, loanApprovalDetail);

        LoanApprovalDetail resultLoanApprovalDetail = loanApprovalDetailRepository.save(loanApprovalDetail);
        return loanApprovalDetailMapper.toLoanApprovalDetailDto(resultLoanApprovalDetail);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<LoanApprovalDetail> loanApprovalDetails = loanApprovalDetailRepository.findAllById(ids);

        for (LoanApprovalDetail loanApprovalDetail : loanApprovalDetails) {
            LoanApprovalDetailDto loanApprovalDetailDto = loanApprovalDetailMapper.toLoanApprovalDetailDto(loanApprovalDetail);
            objectMapper.readerForUpdating(loanApprovalDetailDto).readValue(patchNode);
            loanApprovalDetailMapper.updateWithNull(loanApprovalDetailDto, loanApprovalDetail);
        }

        List<LoanApprovalDetail> resultLoanApprovalDetails = loanApprovalDetailRepository.saveAll(loanApprovalDetails);
        return resultLoanApprovalDetails.stream()
                .map(LoanApprovalDetail::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public LoanApprovalDetailDto delete(@PathVariable UUID id) {
        LoanApprovalDetail loanApprovalDetail = loanApprovalDetailRepository.findById(id).orElse(null);
        if (loanApprovalDetail != null) {
            loanApprovalDetailRepository.delete(loanApprovalDetail);
        }
        return loanApprovalDetailMapper.toLoanApprovalDetailDto(loanApprovalDetail);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        loanApprovalDetailRepository.deleteAllById(ids);
    }
}
