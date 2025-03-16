package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanApprovalDto;
import com.example.project_assouy.entity.LoanApproval;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {LoanApplicationMapper.class, UserMapper.class})
public interface LoanApprovalMapper {
    LoanApproval toEntity(LoanApprovalDto loanApprovalDto);

    LoanApprovalDto toLoanApprovalDto(LoanApproval loanApproval);

    LoanApproval updateWithNull(LoanApprovalDto loanApprovalDto, @MappingTarget LoanApproval loanApproval);
}