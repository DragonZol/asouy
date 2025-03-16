package com.example.project_assouy.dto;

import com.example.project_assouy.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.time.LocalDate;

/**
 * DTO for {@link com.example.project_assouy.entity.UserDocument}
 */
@Value
public class UserDocumentDto {
    User userId;
    Boolean isRussianCitizen;
    @NotNull
    @Size(max = 10)
    String passportSeries;
    @NotNull
    @Size(max = 20)
    String passportNumber;
    @NotNull
    LocalDate passportIssueDate;
}