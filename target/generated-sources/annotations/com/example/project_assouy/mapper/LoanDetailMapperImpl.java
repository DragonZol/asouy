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
    date = "2025-03-16T23:02:45+0800",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
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

        loanDetail.setLoanApplication( loanApplicationMapper.toEntity( loanDetailDto.getLoanApplication() ) );
        loanDetail.setCity( loanDetailDto.getCity() );
        loanDetail.setMonthlyIncome( loanDetailDto.getMonthlyIncome() );
        loanDetail.setWorkExperienceYears( loanDetailDto.getWorkExperienceYears() );
        loanDetail.setRejectionReason( loanDetailDto.getRejectionReason() );

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

        if ( loanDetailDto.getLoanApplication() != null ) {
            if ( loanDetail.getLoanApplication() == null ) {
                loanDetail.setLoanApplication( new LoanApplication() );
            }
            loanApplicationMapper.updateWithNull( loanDetailDto.getLoanApplication(), loanDetail.getLoanApplication() );
        }
        else {
            loanDetail.setLoanApplication( null );
        }
        loanDetail.setCity( loanDetailDto.getCity() );
        loanDetail.setMonthlyIncome( loanDetailDto.getMonthlyIncome() );
        loanDetail.setWorkExperienceYears( loanDetailDto.getWorkExperienceYears() );
        loanDetail.setRejectionReason( loanDetailDto.getRejectionReason() );

        return loanDetail;
    }
}
