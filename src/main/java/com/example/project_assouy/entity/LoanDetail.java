package com.example.project_assouy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "loan_details")
public class LoanDetail {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "loan_application_id")
    private Integer loanApplicationId;

    @Size(max = 100)
    @NotNull
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @NotNull
    @Column(name = "monthly_income", nullable = false, precision = 15, scale = 2)
    private BigDecimal monthlyIncome;

    @NotNull
    @Column(name = "work_experience_years", nullable = false)
    private Integer workExperienceYears;

    @Column(name = "rejection_reason", length = Integer.MAX_VALUE)
    private String rejectionReason;

}