package com.example.project_assouy.dto;

import com.example.project_assouy.entity.User;
import com.example.project_assouy.enums.TicketPriority;
import com.example.project_assouy.enums.TicketStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

/**
 * DTO for {@link com.example.project_assouy.entity.SupportTicket}
 */
@Value
public class SupportTicketDto {
    User user;
    UUID id;
    String userLogin;
    @NotNull
    @Size(max = 200)
    String title;
    @NotNull
    String description;
    TicketPriority priority;
    TicketStatus status;
}