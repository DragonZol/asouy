package com.example.project_assouy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_documents")
public class UserDocument {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

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