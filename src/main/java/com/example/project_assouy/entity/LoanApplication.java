package com.example.project_assouy.entity;

import com.example.project_assouy.enums.StatusApplication;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "loan_applications")
public class LoanApplication extends StandartEntityId {

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @NotNull
    @Column(name = "term_years", nullable = false)
    private Integer termYears;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusApplication status;

    @Column(name = "created_at")
    private Instant createdAt;

}