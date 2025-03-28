package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.LoanApplicationDto;
import com.example.project_assouy.dto.LoanDetailDto;
import com.example.project_assouy.entity.LoanApplication;
import com.example.project_assouy.entity.LoanDetail;
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
public class LoanDetailMapperImpl implements LoanDetailMapper {

    @Autowired
    private LoanApplicationMapper loanApplicationMapper;

    @Override
    public LoanDetail toEntity(LoanDetailDto loanDetailDto) {
        if ( loanDetailDto == null ) {
            return null;
        }

        LoanDetail loanDetail = new LoanDetail();

        loanDetail.setCity( loanDetailDto.getCity() );
        loanDetail.setLoanApplication( loanApplicationMapper.toEntity( loanDetailDto.getLoanApplication() ) );
        loanDetail.setMonthlyIncome( loanDetailDto.getMonthlyIncome() );
        loanDetail.setRejectionReason( loanDetailDto.getRejectionReason() );
        loanDetail.setWorkExperienceYears( loanDetailDto.getWorkExperienceYears() );

        return loanDetail;
    }

    @Override
    public LoanDetailDto toLoanDetailDto(LoanDetail loanDetail) {
        if ( loanDetail == null ) {
            return null;
        }

        LoanApplicationDto loanApplication = null;
        String city = null;
        BigDecimal monthlyIncome = null;
        Integer workExperienceYears = null;
        String rejectionReason = null;

        loanApplication = loanApplicationMapper.toLoanApplicationDto( loanDetail.getLoanApplication() );
        city = loanDetail.getCity();
        monthlyIncome = loanDetail.getMonthlyIncome();
        workExperienceYears = loanDetail.getWorkExperienceYears();
        rejectionReason = loanDetail.getRejectionReason();

        LoanDetailDto loanDetailDto = new LoanDetailDto( loanApplication, city, monthlyIncome, workExperienceYears, rejectionReason );

        return loanDetailDto;
    }

    @Override
    public LoanDetail updateWithNull(LoanDetailDto loanDetailDto, LoanDetail loanDetail) {
        if ( loanDetailDto == null ) {
            return loanDetail;
        }

        loanDetail.setCity( loanDetailDto.getCity() );
        if ( loanDetailDto.getLoanApplication() != null ) {
            if ( loanDetail.getLoanApplication() == null ) {
                loanDetail.setLoanApplication( new LoanApplication() );
            }
            loanApplicationMapper.updateWithNull( loanDetailDto.getLoanApplication(), loanDetail.getLoanApplication() );
        }
        else {
            loanDetail.setLoanApplication( null );
        }
        loanDetail.setMonthlyIncome( loanDetailDto.getMonthlyIncome() );
        loanDetail.setRejectionReason( loanDetailDto.getRejectionReason() );
        loanDetail.setWorkExperienceYears( loanDetailDto.getWorkExperienceYears() );

        return loanDetail;
    }
}
