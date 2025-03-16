package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanDetailDto;
import com.example.project_assouy.entity.LoanDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {LoanApplicationMapper.class})
public interface LoanDetailMapper {
    LoanDetail toEntity(LoanDetailDto loanDetailDto);

    LoanDetailDto toLoanDetailDto(LoanDetail loanDetail);

    LoanDetail updateWithNull(LoanDetailDto loanDetailDto, @MappingTarget LoanDetail loanDetail);
}