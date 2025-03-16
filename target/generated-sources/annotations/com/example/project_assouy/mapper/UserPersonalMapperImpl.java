package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.UserPersonalDto;
import com.example.project_assouy.entity.User;
import com.example.project_assouy.entity.UserPersonal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-16T23:02:45+0800",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class UserPersonalMapperImpl implements UserPersonalMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserPersonal toEntity(UserPersonalDto userPersonalDto) {
        if ( userPersonalDto == null ) {
            return null;
        }

        UserPersonal userPersonal = new UserPersonal();

        userPersonal.setUserId( userMapper.toEntity( userPersonalDto.getUserId() ) );
        userPersonal.setFullName( userPersonalDto.getFullName() );
        userPersonal.setBirthDate( userPersonalDto.getBirthDate() );
        userPersonal.setEmail( userPersonalDto.getEmail() );
        userPersonal.setRegion( userPersonalDto.getRegion() );

        return userPersonal;
    }

    @Override
    public UserPersonalDto toUserPersonalDto(UserPersonal userPersonal) {
        if ( userPersonal == null ) {
            return null;
        }

        User userId = null;
        String fullName = null;
        LocalDate birthDate = null;
        String email = null;
        String region = null;

        userId = userMapper.toEntity( userPersonal.getUserId() );
        fullName = userPersonal.getFullName();
        birthDate = userPersonal.getBirthDate();
        email = userPersonal.getEmail();
        region = userPersonal.getRegion();

        UserPersonalDto userPersonalDto = new UserPersonalDto( userId, fullName, birthDate, email, region );

        return userPersonalDto;
    }

    @Override
    public UserPersonal updateWithNull(UserPersonalDto userPersonalDto, UserPersonal userPersonal) {
        if ( userPersonalDto == null ) {
            return userPersonal;
        }

        userPersonal.setUserId( userMapper.toEntity( userPersonalDto.getUserId() ) );
        userPersonal.setFullName( userPersonalDto.getFullName() );
        userPersonal.setBirthDate( userPersonalDto.getBirthDate() );
        userPersonal.setEmail( userPersonalDto.getEmail() );
        userPersonal.setRegion( userPersonalDto.getRegion() );

        return userPersonal;
    }
}
