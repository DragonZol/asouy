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
    date = "2025-03-28T12:08:09+0800",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.42.0.v20250325-2231, environment: Java 21.0.6 (Eclipse Adoptium)"
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
        loanApprovalDetail.setComment( loanApprovalDetailDto.getComment() );
        loanApprovalDetail.setCreditScore( loanApprovalDetailDto.getCreditScore() );

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
        loanApprovalDetail.setComment( loanApprovalDetailDto.getComment() );
        loanApprovalDetail.setCreditScore( loanApprovalDetailDto.getCreditScore() );

        return loanApprovalDetail;
    }
}
