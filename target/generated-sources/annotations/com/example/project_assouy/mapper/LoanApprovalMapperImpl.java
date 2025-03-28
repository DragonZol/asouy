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
    date = "2025-03-28T10:59:59+0800",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
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

        loanApproval.setLoanApplication( loanApplicationMapper.toEntity( loanApprovalDto.getLoanApplication() ) );
        loanApproval.setApprover( userMapper.toEntity( loanApprovalDto.getApprover() ) );
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

        if ( loanApprovalDto.getLoanApplication() != null ) {
            if ( loanApproval.getLoanApplication() == null ) {
                loanApproval.setLoanApplication( new LoanApplication() );
            }
            loanApplicationMapper.updateWithNull( loanApprovalDto.getLoanApplication(), loanApproval.getLoanApplication() );
        }
        else {
            loanApproval.setLoanApplication( null );
        }
        loanApproval.setApprover( userMapper.toEntity( loanApprovalDto.getApprover() ) );
        loanApproval.setStage( loanApprovalDto.getStage() );
        loanApproval.setStatus( loanApprovalDto.getStatus() );

        return loanApproval;
    }
}
