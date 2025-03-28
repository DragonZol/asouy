package com.example.project_assouy.dto;

import com.example.project_assouy.entity.LoanApplication;
import com.example.project_assouy.enums.StatusApplication;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link com.example.project_assouy.entity.LoanApplication}
 */
@Value
public class LoanApplicationDtoNew {
    UUID id;
    @NotNull
    @Size(max = 50)
    String userLogin;
    @NotNull
    BigDecimal amount;
    @NotNull
    Integer termYears;
    StatusApplication status;
}