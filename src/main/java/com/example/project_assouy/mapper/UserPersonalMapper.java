package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.UserPersonalDto;
import com.example.project_assouy.dto.UserRegistrationDto;
import com.example.project_assouy.entity.UserPersonal;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface UserPersonalMapper {
    UserPersonal toEntity(UserPersonalDto userPersonalDto);

    UserPersonalDto toUserPersonalDto(UserPersonal userPersonal);

    UserPersonal updateWithNull(UserPersonalDto userPersonalDto, @MappingTarget UserPersonal userPersonal);

    @Mapping(source = "userIdRole", target = "userId.role")
    @Mapping(source = "userIdPasswordHash", target = "userId.passwordHash")
    @Mapping(source = "userIdLogin", target = "userId.login")
    @Mapping(source = "userIdPhoneNumber", target = "userId.phoneNumber")
    UserPersonal toEntity(UserRegistrationDto userRegistrationDto);

    @InheritInverseConfiguration(name = "toEntity")
    UserRegistrationDto toUserRegistrationDto(UserPersonal userPersonal);
}