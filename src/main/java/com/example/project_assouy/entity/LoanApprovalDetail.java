package com.example.project_assouy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "loan_approval_details")
public class LoanApprovalDetail {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "approval_id")
    private Integer approvalId;

    @Column(name = "credit_score")
    private Integer creditScore;

    @Column(name = "comment", length = Integer.MAX_VALUE)
    private String comment;

    @Column(name = "updated_at")
    private Instant updatedAt;

}