package com.example.project_assouy.mapper;

import com.example.project_assouy.dto.SupportMessageDto;
import com.example.project_assouy.entity.SupportMessage;
import com.example.project_assouy.entity.SupportTicket;
import com.example.project_assouy.entity.SupportTicketMapper;
import com.example.project_assouy.enums.MessageSender;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-27T18:44:53+0800",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
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

        supportMessage.setTicket( supportTicketMapper.toEntity( supportMessageDto.getTicket() ) );
        supportMessage.setSender( supportMessageDto.getSender() );
        supportMessage.setMessage( supportMessageDto.getMessage() );

        return supportMessage;
    }

    @Override
    public SupportMessageDto toSupportMessageDto(SupportMessage supportMessage) {
        if ( supportMessage == null ) {
            return null;
        }

        SupportTicket ticket = null;
        MessageSender sender = null;
        String message = null;

        ticket = supportTicketMapper.toEntity( supportMessage.getTicket() );
        sender = supportMessage.getSender();
        message = supportMessage.getMessage();

        SupportMessageDto supportMessageDto = new SupportMessageDto( ticket, sender, message );

        return supportMessageDto;
    }

    @Override
    public SupportMessage updateWithNull(SupportMessageDto supportMessageDto, SupportMessage supportMessage) {
        if ( supportMessageDto == null ) {
            return supportMessage;
        }

        supportMessage.setTicket( supportTicketMapper.toEntity( supportMessageDto.getTicket() ) );
        supportMessage.setSender( supportMessageDto.getSender() );
        supportMessage.setMessage( supportMessageDto.getMessage() );

        return supportMessage;
    }
}
