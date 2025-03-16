package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanApprovalDetailDto;
import com.example.project_assouy.entity.LoanApprovalDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {LoanApprovalMapper.class})
public interface LoanApprovalDetailMapper {
    LoanApprovalDetail toEntity(LoanApprovalDetailDto loanApprovalDetailDto);

    LoanApprovalDetailDto toLoanApprovalDetailDto(LoanApprovalDetail loanApprovalDetail);

    LoanApprovalDetail updateWithNull(LoanApprovalDetailDto loanApprovalDetailDto, @MappingTarget LoanApprovalDetail loanApprovalDetail);
}