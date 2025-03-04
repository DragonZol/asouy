package com.example.project_assouy.entity;

import com.example.project_assouy.enums.Stage;
import com.example.project_assouy.enums.StatusApproval;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "loan_approval")
public class LoanApproval extends StandartEntityId{

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "loan_application_id")
    private LoanApplication loanApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_id")
    private User approver;

    @Enumerated(EnumType.STRING)
    @Column(name = "stage", nullable = false)
    private Stage stage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status" )
    private StatusApproval status;



}