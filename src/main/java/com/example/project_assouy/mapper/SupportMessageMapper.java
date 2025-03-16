package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.SupportMessageDto;
import com.example.project_assouy.entity.SupportMessage;
import com.example.project_assouy.entity.SupportTicketMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {SupportTicketMapper.class})
public interface SupportMessageMapper {
    SupportMessage toEntity(SupportMessageDto supportMessageDto);

    SupportMessageDto toSupportMessageDto(SupportMessage supportMessage);

    SupportMessage updateWithNull(SupportMessageDto supportMessageDto, @MappingTarget SupportMessage supportMessage);
}