package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanApplicationDto;
import com.example.project_assouy.dto.LoanApplicationDtoNew;
import com.example.project_assouy.entity.LoanApplication;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanApplicationMapperNew {
    @Mapping(source = "userLogin", target = "user.login")
    LoanApplication toEntity(LoanApplicationDtoNew loanApplicationDtoNew);

    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "id", target = "id")
    LoanApplicationDtoNew toLoanApplicationDto(LoanApplication loanApplication);

    LoanApplication updateWithNull(LoanApplicationDtoNew loanApplicationDto, @MappingTarget LoanApplication loanApplication);

}