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
    date = "2025-03-16T23:02:45+0800",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
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

        loanApplication.setUser( userMapper.toEntity( loanApplicationDto.getUser() ) );
        loanApplication.setAmount( loanApplicationDto.getAmount() );
        loanApplication.setTermYears( loanApplicationDto.getTermYears() );
        loanApplication.setStatus( loanApplicationDto.getStatus() );

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

        loanApplication.setUser( userMapper.toEntity( loanApplicationDto.getUser() ) );
        loanApplication.setAmount( loanApplicationDto.getAmount() );
        loanApplication.setTermYears( loanApplicationDto.getTermYears() );
        loanApplication.setStatus( loanApplicationDto.getStatus() );

        return loanApplication;
    }
}
