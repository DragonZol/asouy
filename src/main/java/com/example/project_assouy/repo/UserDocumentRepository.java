package com.example.project_assouy.repo;

import com.example.project_assouy.entity.UserDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDocumentRepository extends JpaRepository<UserDocument, UUID> {
}