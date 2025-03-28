package com.example.project_assouy.entity;

import com.example.project_assouy.dto.SupportTicketDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {com.example.project_assouy.mapper.UserMapper.class})
public interface SupportTicketMapper {
    SupportTicket toEntity(SupportTicket supportTicket);

    @Mapping(source = "userLogin", target = "user.login")
    SupportTicket toEntity(SupportTicketDto supportTicketDto);

    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "id", target = "id")
    SupportTicketDto toSupportTicketDto(SupportTicket supportTicket);

    SupportTicket updateWithNull(SupportTicketDto supportTicketDto, @MappingTarget SupportTicket supportTicket);
}