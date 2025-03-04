package com.example.project_assouy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "loan_approval_details")
public class LoanApprovalDetail extends StandartEntityId{

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "approval_id")
    private LoanApproval approvalId;

    @Column(name = "credit_score")
    private Integer creditScore;

    @Column(name = "comment", length = Integer.MAX_VALUE)
    private String comment;

    @Column(name = "updated_at")
    private Instant updatedAt;

}