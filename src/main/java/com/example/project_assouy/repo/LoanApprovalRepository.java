package com.example.project_assouy.repo;

import com.example.project_assouy.entity.LoanApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanApprovalRepository extends JpaRepository<LoanApproval, UUID> {
}