package com.example.project_assouy.entity;

import com.example.project_assouy.dto.SupportTicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {com.example.project_assouy.mapper.UserMapper.class})
public interface SupportTicketMapper {
    SupportTicket toEntity(SupportTicket supportTicket);

    SupportTicket toEntity(SupportTicketDto supportTicketDto);

    SupportTicketDto toSupportTicketDto(SupportTicket supportTicket);

    SupportTicket updateWithNull(SupportTicketDto supportTicketDto, @MappingTarget SupportTicket supportTicket);
}