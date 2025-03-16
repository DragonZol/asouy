package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanApprovalDetailDto;
import com.example.project_assouy.dto.LoanApprovalDto;
import com.example.project_assouy.entity.LoanApproval;
import com.example.project_assouy.entity.LoanApprovalDetail;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-16T23:02:45+0800",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class LoanApprovalDetailMapperImpl implements LoanApprovalDetailMapper {

    @Autowired
    private LoanApprovalMapper loanApprovalMapper;

    @Override
    public LoanApprovalDetail toEntity(LoanApprovalDetailDto loanApprovalDetailDto) {
        if ( loanApprovalDetailDto == null ) {
            return null;
        }

        LoanApprovalDetail loanApprovalDetail = new LoanApprovalDetail();

        loanApprovalDetail.setApprovalId( loanApprovalMapper.toEntity( loanApprovalDetailDto.getApprovalId() ) );
        loanApprovalDetail.setCreditScore( loanApprovalDetailDto.getCreditScore() );
        loanApprovalDetail.setComment( loanApprovalDetailDto.getComment() );

        return loanApprovalDetail;
    }

    @Override
    public LoanApprovalDetailDto toLoanApprovalDetailDto(LoanApprovalDetail loanApprovalDetail) {
        if ( loanApprovalDetail == null ) {
            return null;
        }

        LoanApprovalDto approvalId = null;
        Integer creditScore = null;
        String comment = null;

        approvalId = loanApprovalMapper.toLoanApprovalDto( loanApprovalDetail.getApprovalId() );
        creditScore = loanApprovalDetail.getCreditScore();
        comment = loanApprovalDetail.getComment();

        LoanApprovalDetailDto loanApprovalDetailDto = new LoanApprovalDetailDto( approvalId, creditScore, comment );

        return loanApprovalDetailDto;
    }

    @Override
    public LoanApprovalDetail updateWithNull(LoanApprovalDetailDto loanApprovalDetailDto, LoanApprovalDetail loanApprovalDetail) {
        if ( loanApprovalDetailDto == null ) {
            return loanApprovalDetail;
        }

        if ( loanApprovalDetailDto.getApprovalId() != null ) {
            if ( loanApprovalDetail.getApprovalId() == null ) {
                loanApprovalDetail.setApprovalId( new LoanApproval() );
            }
            loanApprovalMapper.updateWithNull( loanApprovalDetailDto.getApprovalId(), loanApprovalDetail.getApprovalId() );
        }
        else {
            loanApprovalDetail.setApprovalId( null );
        }
        loanApprovalDetail.setCreditScore( loanApprovalDetailDto.getCreditScore() );
        loanApprovalDetail.setComment( loanApprovalDetailDto.getComment() );

        return loanApprovalDetail;
    }
}
