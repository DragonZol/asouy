package com.example.project_assouy.repo;

import com.example.project_assouy.entity.SupportMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupportMessageRepository extends JpaRepository<SupportMessage, UUID> {
    Optional<Object> findByTicket_Id(UUID ticketId);

    List<SupportMessage> findSupportMessagesByTicket_Id(UUID ticketId);


    SupportMessage findSupportMessageByTicket_Id(UUID id);
}