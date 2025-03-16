package com.example.project_assouy.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.math.BigDecimal;

/**
 * DTO for {@link com.example.project_assouy.entity.LoanDetail}
 */
@Value
public class LoanDetailDto {
    LoanApplicationDto loanApplication;
    @NotNull
    @Size(max = 100)
    String city;
    @NotNull
    BigDecimal monthlyIncome;
    @NotNull
    Integer workExperienceYears;
    String rejectionReason;
}