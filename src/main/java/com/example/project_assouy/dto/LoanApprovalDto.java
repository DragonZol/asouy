package com.example.project_assouy.dto;

import com.example.project_assouy.entity.User;
import com.example.project_assouy.enums.Stage;
import com.example.project_assouy.enums.StatusApproval;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link com.example.project_assouy.entity.LoanApproval}
 */
@Value
public class LoanApprovalDto {
    LoanApplicationDto loanApplication;
    User approver;
    Stage stage;
    @NotNull(message = "Не может быть NULL")
    StatusApproval status;
}