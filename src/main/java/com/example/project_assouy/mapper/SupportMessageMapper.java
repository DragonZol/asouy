package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.SupportMessageDto;
import com.example.project_assouy.entity.SupportMessage;
import com.example.project_assouy.entity.SupportTicketMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {SupportTicketMapper.class})
public interface SupportMessageMapper {
    @Mapping(source = "id", target = "id")
    SupportMessage toEntity(SupportMessageDto supportMessageDto);
    @Mapping(source = "id", target = "id")
    SupportMessageDto toSupportMessageDto(SupportMessage supportMessage);

    SupportMessage updateWithNull(SupportMessageDto supportMessageDto, @MappingTarget SupportMessage supportMessage);
}