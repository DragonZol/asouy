package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.SupportMessageDto;
import com.example.project_assouy.entity.SupportMessage;
import com.example.project_assouy.entity.SupportTicket;
import com.example.project_assouy.entity.SupportTicketMapper;
import com.example.project_assouy.enums.MessageSender;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T12:08:09+0800",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.42.0.v20250325-2231, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class SupportMessageMapperImpl implements SupportMessageMapper {

    @Autowired
    private SupportTicketMapper supportTicketMapper;

    @Override
    public SupportMessage toEntity(SupportMessageDto supportMessageDto) {
        if ( supportMessageDto == null ) {
            return null;
        }

        SupportMessage supportMessage = new SupportMessage();

        supportMessage.setId( supportMessageDto.getId() );
        supportMessage.setMessage( supportMessageDto.getMessage() );
        supportMessage.setSender( supportMessageDto.getSender() );
        supportMessage.setTicket( supportTicketMapper.toEntity( supportMessageDto.getTicket() ) );

        return supportMessage;
    }

    @Override
    public SupportMessageDto toSupportMessageDto(SupportMessage supportMessage) {
        if ( supportMessage == null ) {
            return null;
        }

        UUID id = null;
        SupportTicket ticket = null;
        MessageSender sender = null;
        String message = null;

        id = supportMessage.getId();
        ticket = supportTicketMapper.toEntity( supportMessage.getTicket() );
        sender = supportMessage.getSender();
        message = supportMessage.getMessage();

        SupportMessageDto supportMessageDto = new SupportMessageDto( ticket, id, sender, message );

        return supportMessageDto;
    }

    @Override
    public SupportMessage updateWithNull(SupportMessageDto supportMessageDto, SupportMessage supportMessage) {
        if ( supportMessageDto == null ) {
            return supportMessage;
        }

        supportMessage.setId( supportMessageDto.getId() );
        supportMessage.setMessage( supportMessageDto.getMessage() );
        supportMessage.setSender( supportMessageDto.getSender() );
        supportMessage.setTicket( supportTicketMapper.toEntity( supportMessageDto.getTicket() ) );

        return supportMessage;
    }
}
