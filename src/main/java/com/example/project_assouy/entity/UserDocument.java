package com.example.project_assouy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_documents")
public class UserDocument extends StandartEntityId {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "is_russian_citizen")
    private Boolean isRussianCitizen;

    @Size(max = 10)
    @NotNull
    @Column(name = "passport_series", nullable = false, length = 10)
    private String passportSeries;

    @Size(max = 20)
    @NotNull
    @Column(name = "passport_number", nullable = false, length = 20)
    private String passportNumber;

    @NotNull
    @Column(name = "passport_issue_date", nullable = false)
    private LocalDate passportIssueDate;

}