package com.example.project_assouy.dto;

import com.example.project_assouy.entity.SupportTicket;
import com.example.project_assouy.enums.MessageSender;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link com.example.project_assouy.entity.SupportMessage}
 */
@Value
public class SupportMessageDto {
    SupportTicket ticket;
    MessageSender sender;
    @NotNull
    String message;
}