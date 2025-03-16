package com.example.project_assouy.repo;

import com.example.project_assouy.entity.SupportMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupportMessageRepository extends JpaRepository<SupportMessage, UUID> {
}