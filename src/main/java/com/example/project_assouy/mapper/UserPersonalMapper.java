package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.UserPersonalDto;
import com.example.project_assouy.entity.UserPersonal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface UserPersonalMapper {
    UserPersonal toEntity(UserPersonalDto userPersonalDto);

    UserPersonalDto toUserPersonalDto(UserPersonal userPersonal);

    UserPersonal updateWithNull(UserPersonalDto userPersonalDto, @MappingTarget UserPersonal userPersonal);
}