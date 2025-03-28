package com.example.project_assouy.controller;

import com.example.project_assouy.dto.LoanApplicationDtoNew;
import com.example.project_assouy.entity.LoanApplication;
import com.example.project_assouy.enums.StatusApplication;
import com.example.project_assouy.enums.StatusApproval;
import com.example.project_assouy.mapper.LoanApplicationMapperNew;
import com.example.project_assouy.repo.LoanApplicationRepository;
import com.example.project_assouy.repo.UserRepository;
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

@RestController
@RequestMapping("/api/loanApplications")
@RequiredArgsConstructor
public class LoanApplicationResource {

    private final LoanApplicationRepository loanApplicationRepository;

    private final LoanApplicationMapperNew loanApplicationMapperNew;

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @GetMapping
    public List<LoanApplicationDtoNew> getAll() {
        List<LoanApplication> loanApplications = loanApplicationRepository.findAll();
        return loanApplications.stream()
                .map(loanApplicationMapperNew::toLoanApplicationDto)
                .toList();
    }

    @GetMapping("/{id}")
    public LoanApplicationDtoNew getOne(@PathVariable UUID id) {
        Optional<LoanApplication> loanApplicationOptional = loanApplicationRepository.findById(id);
        return loanApplicationMapperNew.toLoanApplicationDto(loanApplicationOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<LoanApplicationDtoNew> getMany(@RequestParam List<UUID> ids) {
        List<LoanApplication> loanApplications = loanApplicationRepository.findAllById(ids);
        return loanApplications.stream()
                .map(loanApplicationMapperNew::toLoanApplicationDto)
                .toList();
    }

    @PostMapping
    public LoanApplicationDtoNew create(@RequestBody @Valid LoanApplicationDtoNew dto) {
        LoanApplication loanApplication = loanApplicationMapperNew.toEntity(dto);
        loanApplication.setUser(userRepository.findUserByLogin(dto.getUserLogin()));
        loanApplication.setCreateTs(LocalDateTime.now());
        loanApplication.setCreatedBy("admin");
        LoanApplication resultLoanApplication = loanApplicationRepository.save(loanApplication);
        return loanApplicationMapperNew.toLoanApplicationDto(resultLoanApplication);
    }

    @PostMapping("/{id}/approve")
    public LoanApplicationDtoNew approve(@PathVariable UUID id) {
        // Находим заявку по id, если не найдено - выбрасываем исключение
        LoanApplication loanApplication = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Entity with id " + id + " not found"));

        // Обновляем статус заявки
        // Предположим, что поле status имеет тип StatusApproval, либо StatusApplication
        loanApplication.setStatus(StatusApplication.APPROVED);

        // Сохраняем изменённую заявку, если это требуется (если статус меняется в БД)
        LoanApplication resultLoanApplication = loanApplicationRepository.save(loanApplication);

        // Преобразуем сохраненную сущность в DTO и возвращаем
        return loanApplicationMapperNew.toLoanApplicationDto(resultLoanApplication);
    }
    @PostMapping("/{id}/reject")
    public LoanApplicationDtoNew reject(@PathVariable UUID id) {
        // Находим заявку по id, если не найдено - выбрасываем исключение
        LoanApplication loanApplication = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Entity with id " + id + " not found"));

        // Обновляем статус заявки
        // Предположим, что поле status имеет тип StatusApproval, либо StatusApplication
        loanApplication.setStatus(StatusApplication.REJECTED);
        loanApplication.setDeletedBy("admin");
        loanApplication.setDeleteTs(LocalDateTime.now());

        // Сохраняем изменённую заявку, если это требуется (если статус меняется в БД)
        LoanApplication resultLoanApplication = loanApplicationRepository.save(loanApplication);

        // Преобразуем сохраненную сущность в DTO и возвращаем
        return loanApplicationMapperNew.toLoanApplicationDto(resultLoanApplication);
    }

    @PatchMapping("/{id}")
    public LoanApplicationDtoNew patch(@PathVariable UUID id, @RequestBody JsonNode patchNode) throws IOException {
        LoanApplication loanApplication = loanApplicationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        LoanApplicationDtoNew loanApplicationDto = loanApplicationMapperNew.toLoanApplicationDto(loanApplication);
        objectMapper.readerForUpdating(loanApplicationDto).readValue(patchNode);
        loanApplicationMapperNew.updateWithNull(loanApplicationDto, loanApplication);

        LoanApplication resultLoanApplication = loanApplicationRepository.save(loanApplication);
        return loanApplicationMapperNew.toLoanApplicationDto(resultLoanApplication);
    }

    @PatchMapping
    public List<UUID> patchMany(@RequestParam @Valid List<UUID> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<LoanApplication> loanApplications = loanApplicationRepository.findAllById(ids);

        for (LoanApplication loanApplication : loanApplications) {
            LoanApplicationDtoNew loanApplicationDto = loanApplicationMapperNew.toLoanApplicationDto(loanApplication);
            objectMapper.readerForUpdating(loanApplicationDto).readValue(patchNode);
            loanApplicationMapperNew.updateWithNull(loanApplicationDto, loanApplication);
        }

        List<LoanApplication> resultLoanApplications = loanApplicationRepository.saveAll(loanApplications);
        return resultLoanApplications.stream()
                .map(LoanApplication::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public LoanApplicationDtoNew delete(@PathVariable UUID id) {
        LoanApplication loanApplication = loanApplicationRepository.findById(id).orElse(null);
        if (loanApplication != null) {
            loanApplicationRepository.delete(loanApplication);
        }
        return loanApplicationMapperNew.toLoanApplicationDto(loanApplication);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<UUID> ids) {
        loanApplicationRepository.deleteAllById(ids);
    }
}
