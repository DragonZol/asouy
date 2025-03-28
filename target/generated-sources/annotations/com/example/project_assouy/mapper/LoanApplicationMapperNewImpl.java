package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanApplicationDtoNew;
import com.example.project_assouy.entity.LoanApplication;
import com.example.project_assouy.entity.User;
import com.example.project_assouy.enums.StatusApplication;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T12:08:10+0800",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.42.0.v20250325-2231, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class LoanApplicationMapperNewImpl implements LoanApplicationMapperNew {

    @Override
    public LoanApplication toEntity(LoanApplicationDtoNew loanApplicationDtoNew) {
        if ( loanApplicationDtoNew == null ) {
            return null;
        }

        LoanApplication loanApplication = new LoanApplication();

        loanApplication.setUser( loanApplicationDtoNewToUser( loanApplicationDtoNew ) );
        loanApplication.setId( loanApplicationDtoNew.getId() );
        loanApplication.setAmount( loanApplicationDtoNew.getAmount() );
        loanApplication.setStatus( loanApplicationDtoNew.getStatus() );
        loanApplication.setTermYears( loanApplicationDtoNew.getTermYears() );

        return loanApplication;
    }

    @Override
    public LoanApplicationDtoNew toLoanApplicationDto(LoanApplication loanApplication) {
        if ( loanApplication == null ) {
            return null;
        }

        String userLogin = null;
        UUID id = null;
        BigDecimal amount = null;
        Integer termYears = null;
        StatusApplication status = null;

        userLogin = loanApplicationUserLogin( loanApplication );
        id = loanApplication.getId();
        amount = loanApplication.getAmount();
        termYears = loanApplication.getTermYears();
        status = loanApplication.getStatus();

        LoanApplicationDtoNew loanApplicationDtoNew = new LoanApplicationDtoNew( id, userLogin, amount, termYears, status );

        return loanApplicationDtoNew;
    }

    @Override
    public LoanApplication updateWithNull(LoanApplicationDtoNew loanApplicationDto, LoanApplication loanApplication) {
        if ( loanApplicationDto == null ) {
            return loanApplication;
        }

        loanApplication.setId( loanApplicationDto.getId() );
        loanApplication.setAmount( loanApplicationDto.getAmount() );
        loanApplication.setStatus( loanApplicationDto.getStatus() );
        loanApplication.setTermYears( loanApplicationDto.getTermYears() );

        return loanApplication;
    }

    protected User loanApplicationDtoNewToUser(LoanApplicationDtoNew loanApplicationDtoNew) {
        if ( loanApplicationDtoNew == null ) {
            return null;
        }

        User user = new User();

        user.setLogin( loanApplicationDtoNew.getUserLogin() );

        return user;
    }

    private String loanApplicationUserLogin(LoanApplication loanApplication) {
        User user = loanApplication.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getLogin();
    }
}
