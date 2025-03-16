package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.UserDocumentDto;
import com.example.project_assouy.entity.UserDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface UserDocumentMapper {
    UserDocument toEntity(UserDocumentDto userDocumentDto);

    UserDocumentDto toUserDocumentDto(UserDocument userDocument);

    UserDocument updateWithNull(UserDocumentDto userDocumentDto, @MappingTarget UserDocument userDocument);
}