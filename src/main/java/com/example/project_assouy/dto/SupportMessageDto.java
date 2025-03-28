package com.example.project_assouy.dto;

import com.example.project_assouy.entity.SupportTicket;
import com.example.project_assouy.enums.MessageSender;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

/**
 * DTO for {@link com.example.project_assouy.entity.SupportMessage}
 */
@Value
public class SupportMessageDto {
    SupportTicket ticket;
    UUID id;
    MessageSender sender;
    @NotNull
    String message;
}