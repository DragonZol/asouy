package com.example.project_assouy.mapper;

import com.example.project_assouy.entity.User;

@org.mapstruct.Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    User toEntity(User user);
}