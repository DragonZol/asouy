package com.example.project_assouy.entity;

import com.example.project_assouy.enums.MessageSender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "support_messages")
public class SupportMessage extends StandartEntityId {

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ticket_id")
    private SupportTicket ticket;

   @Enumerated(EnumType.STRING)
   @Column(name = "sender_type")
    private MessageSender sender;

    @NotNull
    @Column(name = "message", nullable = false, length = Integer.MAX_VALUE)
    private String message;


}