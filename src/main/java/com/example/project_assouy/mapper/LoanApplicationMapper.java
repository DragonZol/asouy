package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanApplicationDto;
import com.example.project_assouy.entity.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {UserMapper.class})
public interface LoanApplicationMapper {
    LoanApplication toEntity(LoanApplicationDto loanApplicationDto);

    LoanApplicationDto toLoanApplicationDto(LoanApplication loanApplication);

    LoanApplication updateWithNull(LoanApplicationDto loanApplicationDto, @MappingTarget LoanApplication loanApplication);
}