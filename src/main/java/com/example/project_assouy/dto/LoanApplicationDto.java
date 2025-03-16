package com.example.project_assouy.dto;

import com.example.project_assouy.entity.User;
import com.example.project_assouy.enums.StatusApplication;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

/**
 * DTO for {@link com.example.project_assouy.entity.LoanApplication}
 */
@Value
public class LoanApplicationDto {
    User user;
    @NotNull
    BigDecimal amount;
    @NotNull
    Integer termYears;
    @NotNull(message = "Статус не должен быть NULL")
    StatusApplication status;
}