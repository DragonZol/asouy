package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.UserPersonalDto;
import com.example.project_assouy.dto.UserRegistrationDto;
import com.example.project_assouy.entity.User;
import com.example.project_assouy.entity.UserPersonal;
import com.example.project_assouy.enums.Role;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T10:59:59+0800",
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

    @Override
    public UserPersonal toEntity(UserRegistrationDto userRegistrationDto) {
        if ( userRegistrationDto == null ) {
            return null;
        }

        UserPersonal userPersonal = new UserPersonal();

        userPersonal.setUserId( userRegistrationDtoToUser( userRegistrationDto ) );
        userPersonal.setFullName( userRegistrationDto.getFullName() );
        userPersonal.setBirthDate( userRegistrationDto.getBirthDate() );
        userPersonal.setEmail( userRegistrationDto.getEmail() );
        userPersonal.setRegion( userRegistrationDto.getRegion() );

        return userPersonal;
    }

    @Override
    public UserRegistrationDto toUserRegistrationDto(UserPersonal userPersonal) {
        if ( userPersonal == null ) {
            return null;
        }

        Role userIdRole = null;
        String userIdPasswordHash = null;
        String userIdLogin = null;
        String userIdPhoneNumber = null;
        String fullName = null;
        LocalDate birthDate = null;
        String email = null;
        String region = null;

        userIdRole = userPersonalUserIdRole( userPersonal );
        userIdPasswordHash = userPersonalUserIdPasswordHash( userPersonal );
        userIdLogin = userPersonalUserIdLogin( userPersonal );
        userIdPhoneNumber = userPersonalUserIdPhoneNumber( userPersonal );
        fullName = userPersonal.getFullName();
        birthDate = userPersonal.getBirthDate();
        email = userPersonal.getEmail();
        region = userPersonal.getRegion();

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto( userIdPhoneNumber, userIdLogin, userIdPasswordHash, userIdRole, fullName, birthDate, email, region );

        return userRegistrationDto;
    }

    protected User userRegistrationDtoToUser(UserRegistrationDto userRegistrationDto) {
        if ( userRegistrationDto == null ) {
            return null;
        }

        User user = new User();

        user.setRole( userRegistrationDto.getUserIdRole() );
        user.setPasswordHash( userRegistrationDto.getUserIdPasswordHash() );
        user.setLogin( userRegistrationDto.getUserIdLogin() );
        user.setPhoneNumber( userRegistrationDto.getUserIdPhoneNumber() );

        return user;
    }

    private Role userPersonalUserIdRole(UserPersonal userPersonal) {
        User userId = userPersonal.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getRole();
    }

    private String userPersonalUserIdPasswordHash(UserPersonal userPersonal) {
        User userId = userPersonal.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getPasswordHash();
    }

    private String userPersonalUserIdLogin(UserPersonal userPersonal) {
        User userId = userPersonal.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getLogin();
    }

    private String userPersonalUserIdPhoneNumber(UserPersonal userPersonal) {
        User userId = userPersonal.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getPhoneNumber();
    }
}
