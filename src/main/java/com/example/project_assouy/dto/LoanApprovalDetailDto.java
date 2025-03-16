package com.example.project_assouy.dto;

import lombok.Value;

/**
 * DTO for {@link com.example.project_assouy.entity.LoanApprovalDetail}
 */
@Value
public class LoanApprovalDetailDto {
    LoanApprovalDto approvalId;
    Integer creditScore;
    String comment;
}