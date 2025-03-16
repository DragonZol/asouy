package com.example.project_assouy.repo;

import com.example.project_assouy.entity.LoanDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanDetailRepository extends JpaRepository<LoanDetail, UUID> {
}