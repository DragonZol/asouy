package com.example.project_assouy.controller;

import com.example.project_assouy.dto.LoanDetailDto;
import com.example.project_assouy.entity.LoanDetail;
import com.example.project_assouy.mapper.LoanDetailMapper;
import com.example.project_assouy.repo.LoanDetailRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/crud/loanDetails")
@RequiredArgsConstructor
public class LoanDetailResource {

    private final LoanDetailRepository loanDetailRepository;

    private final LoanDetailMapper loanDetailMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public PagedModel<LoanDetailDto> getAll(Pageable pageable) {
        Page<LoanDetail> loanDetails = loanDetailRepository.findAll(pageable);
        Page<LoanDetailDto> loanDetailDtoPage = loanDetails.map(loanDetailMapper::toLoanDetailDto);
        return new PagedModel<>(loanDetailDtoPage);
    }

    @GetMapping("/{id}")
    public LoanDetailDto getOne(@PathVariable UUID id) {
        Optional<LoanDetail> loanDetailOptional = loanDetailRepository.findById(id);
        return loanDetailMapper.toLoanDetailDto(loanDetailOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<LoanDetailDto> getMany(@RequestParam List<UUID> ids) {
        List<LoanDetail> loanDetails = loanDetailRepository.findAllById(ids);
        return loanDetails.stream()
                .map(loanDetailMapper::toLoanDetailDto)
                .toList();
    }

    @PostMapping
    public LoanDetailDto create(@RequestBody @Valid LoanDetailDto dto) {
        LoanDetail loanDetail = loanDetailMapper.toEntity(dto);
        LoanDetail resultLoanDetail = loanDetailRepository.save(loanDetail);
        return loanDetailMapper.toLoanDetailDto(resultLoanDetail);
    }

    @PatchMapping("/{id}")
    public LoanDetailDto patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        LoanDetail loanDetail = loanDetailRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        LoanDetailDto loanDetailDto = loanDetailMapper.toLoanDetailDto(loanDetail);
        objectMapper.readerForUpdating(loanDetailDto).readValue(patchNode);
        loanDetailMapper.updateWithNull(loanDetailDto, loanDetail);

        LoanDetail resultLoanDetail = loanDetailRepository.save(loanDetail);
        return loanDetailMapper.toLoanDetailDto(resultLoanDetail);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam @Valid List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<LoanDetail> loanDetails = loanDetailRepository.findAllById(ids);

        for (LoanDetail loanDetail : loanDetails) {
            LoanDetailDto loanDetailDto = loanDetailMapper.toLoanDetailDto(loanDetail);
            objectMapper.readerForUpdating(loanDetailDto).readValue(patchNode);
            loanDetailMapper.updateWithNull(loanDetailDto, loanDetail);
        }

        List<LoanDetail> resultLoanDetails = loanDetailRepository.saveAll(loanDetails);
        return resultLoanDetails.stream()
                .map(LoanDetail::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public LoanDetailDto delete(@PathVariable UUID id) {
        LoanDetail loanDetail = loanDetailRepository.findById(id).orElse(null);
        if (loanDetail != null) {
            loanDetailRepository.delete(loanDetail);
        }
        return loanDetailMapper.toLoanDetailDto(loanDetail);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        loanDetailRepository.deleteAllById(ids);
    }
}
