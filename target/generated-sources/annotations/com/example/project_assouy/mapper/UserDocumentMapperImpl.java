package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.UserDocumentDto;
import com.example.project_assouy.entity.User;
import com.example.project_assouy.entity.UserDocument;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T12:08:10+0800",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.42.0.v20250325-2231, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class UserDocumentMapperImpl implements UserDocumentMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDocument toEntity(UserDocumentDto userDocumentDto) {
        if ( userDocumentDto == null ) {
            return null;
        }

        UserDocument userDocument = new UserDocument();

        userDocument.setIsRussianCitizen( userDocumentDto.getIsRussianCitizen() );
        userDocument.setPassportIssueDate( userDocumentDto.getPassportIssueDate() );
        userDocument.setPassportNumber( userDocumentDto.getPassportNumber() );
        userDocument.setPassportSeries( userDocumentDto.getPassportSeries() );
        userDocument.setUserId( userMapper.toEntity( userDocumentDto.getUserId() ) );

        return userDocument;
    }

    @Override
    public UserDocumentDto toUserDocumentDto(UserDocument userDocument) {
        if ( userDocument == null ) {
            return null;
        }

        User userId = null;
        Boolean isRussianCitizen = null;
        String passportSeries = null;
        String passportNumber = null;
        LocalDate passportIssueDate = null;

        userId = userMapper.toEntity( userDocument.getUserId() );
        isRussianCitizen = userDocument.getIsRussianCitizen();
        passportSeries = userDocument.getPassportSeries();
        passportNumber = userDocument.getPassportNumber();
        passportIssueDate = userDocument.getPassportIssueDate();

        UserDocumentDto userDocumentDto = new UserDocumentDto( userId, isRussianCitizen, passportSeries, passportNumber, passportIssueDate );

        return userDocumentDto;
    }

    @Override
    public UserDocument updateWithNull(UserDocumentDto userDocumentDto, UserDocument userDocument) {
        if ( userDocumentDto == null ) {
            return userDocument;
        }

        userDocument.setIsRussianCitizen( userDocumentDto.getIsRussianCitizen() );
        userDocument.setPassportIssueDate( userDocumentDto.getPassportIssueDate() );
        userDocument.setPassportNumber( userDocumentDto.getPassportNumber() );
        userDocument.setPassportSeries( userDocumentDto.getPassportSeries() );
        userDocument.setUserId( userMapper.toEntity( userDocumentDto.getUserId() ) );

        return userDocument;
    }
}
