package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanApplicationDto;
import com.example.project_assouy.dto.LoanApprovalDto;
import com.example.project_assouy.entity.LoanApplication;
import com.example.project_assouy.entity.LoanApproval;
import com.example.project_assouy.entity.User;
import com.example.project_assouy.enums.Stage;
import com.example.project_assouy.enums.StatusApproval;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T12:08:09+0800",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.42.0.v20250325-2231, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class LoanApprovalMapperImpl implements LoanApprovalMapper {

    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoanApproval toEntity(LoanApprovalDto loanApprovalDto) {
        if ( loanApprovalDto == null ) {
            return null;
        }

        LoanApproval loanApproval = new LoanApproval();

        loanApproval.setApprover( userMapper.toEntity( loanApprovalDto.getApprover() ) );
        loanApproval.setLoanApplication( loanApplicationMapper.toEntity( loanApprovalDto.getLoanApplication() ) );
        loanApproval.setStage( loanApprovalDto.getStage() );
        loanApproval.setStatus( loanApprovalDto.getStatus() );

        return loanApproval;
    }

    @Override
    public LoanApprovalDto toLoanApprovalDto(LoanApproval loanApproval) {
        if ( loanApproval == null ) {
            return null;
        }

        LoanApplicationDto loanApplication = null;
        User approver = null;
        Stage stage = null;
        StatusApproval status = null;

        loanApplication = loanApplicationMapper.toLoanApplicationDto( loanApproval.getLoanApplication() );
        approver = userMapper.toEntity( loanApproval.getApprover() );
        stage = loanApproval.getStage();
        status = loanApproval.getStatus();

        LoanApprovalDto loanApprovalDto = new LoanApprovalDto( loanApplication, approver, stage, status );

        return loanApprovalDto;
    }

    @Override
    public LoanApproval updateWithNull(LoanApprovalDto loanApprovalDto, LoanApproval loanApproval) {
        if ( loanApprovalDto == null ) {
            return loanApproval;
        }

        loanApproval.setApprover( userMapper.toEntity( loanApprovalDto.getApprover() ) );
        if ( loanApprovalDto.getLoanApplication() != null ) {
            if ( loanApproval.getLoanApplication() == null ) {
                loanApproval.setLoanApplication( new LoanApplication() );
            }
            loanApplicationMapper.updateWithNull( loanApprovalDto.getLoanApplication(), loanApproval.getLoanApplication() );
        }
        else {
            loanApproval.setLoanApplication( null );
        }
        loanApproval.setStage( loanApprovalDto.getStage() );
        loanApproval.setStatus( loanApprovalDto.getStatus() );

        return loanApproval;
    }
}
