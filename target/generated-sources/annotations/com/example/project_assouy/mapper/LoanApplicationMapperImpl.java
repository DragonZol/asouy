package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanApplicationDto;
import com.example.project_assouy.entity.LoanApplication;
import com.example.project_assouy.entity.User;
import com.example.project_assouy.enums.StatusApplication;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T12:08:10+0800",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.42.0.v20250325-2231, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class LoanApplicationMapperImpl implements LoanApplicationMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoanApplication toEntity(LoanApplicationDto loanApplicationDto) {
        if ( loanApplicationDto == null ) {
            return null;
        }

        LoanApplication loanApplication = new LoanApplication();

        loanApplication.setAmount( loanApplicationDto.getAmount() );
        loanApplication.setStatus( loanApplicationDto.getStatus() );
        loanApplication.setTermYears( loanApplicationDto.getTermYears() );
        loanApplication.setUser( userMapper.toEntity( loanApplicationDto.getUser() ) );

        return loanApplication;
    }

    @Override
    public LoanApplicationDto toLoanApplicationDto(LoanApplication loanApplication) {
        if ( loanApplication == null ) {
            return null;
        }

        User user = null;
        BigDecimal amount = null;
        Integer termYears = null;
        StatusApplication status = null;

        user = userMapper.toEntity( loanApplication.getUser() );
        amount = loanApplication.getAmount();
        termYears = loanApplication.getTermYears();
        status = loanApplication.getStatus();

        LoanApplicationDto loanApplicationDto = new LoanApplicationDto( user, amount, termYears, status );

        return loanApplicationDto;
    }

    @Override
    public LoanApplication updateWithNull(LoanApplicationDto loanApplicationDto, LoanApplication loanApplication) {
        if ( loanApplicationDto == null ) {
            return loanApplication;
        }

        loanApplication.setAmount( loanApplicationDto.getAmount() );
        loanApplication.setStatus( loanApplicationDto.getStatus() );
        loanApplication.setTermYears( loanApplicationDto.getTermYears() );
        loanApplication.setUser( userMapper.toEntity( loanApplicationDto.getUser() ) );

        return loanApplication;
    }
}
