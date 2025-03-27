package com.example.project_assouy.entity;

import com.example.project_assouy.dto.SupportTicketDto;
import com.example.project_assouy.enums.TicketPriority;
import com.example.project_assouy.enums.TicketStatus;
import com.example.project_assouy.mapper.UserMapper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-27T18:44:53+0800",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class SupportTicketMapperImpl implements SupportTicketMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SupportTicket toEntity(SupportTicket supportTicket) {
        if ( supportTicket == null ) {
            return null;
        }

        SupportTicket supportTicket1 = new SupportTicket();

        supportTicket1.setCreateTs( supportTicket.getCreateTs() );
        supportTicket1.setCreatedBy( supportTicket.getCreatedBy() );
        supportTicket1.setUpdateTs( supportTicket.getUpdateTs() );
        supportTicket1.setUpdatedBy( supportTicket.getUpdatedBy() );
        supportTicket1.setDeleteTs( supportTicket.getDeleteTs() );
        supportTicket1.setDeletedBy( supportTicket.getDeletedBy() );
        supportTicket1.setId( supportTicket.getId() );
        supportTicket1.setUser( userMapper.toEntity( supportTicket.getUser() ) );
        supportTicket1.setTitle( supportTicket.getTitle() );
        supportTicket1.setDescription( supportTicket.getDescription() );
        supportTicket1.setPriority( supportTicket.getPriority() );
        supportTicket1.setStatus( supportTicket.getStatus() );

        return supportTicket1;
    }

    @Override
    public SupportTicket toEntity(SupportTicketDto supportTicketDto) {
        if ( supportTicketDto == null ) {
            return null;
        }

        SupportTicket supportTicket = new SupportTicket();

        supportTicket.setUser( userMapper.toEntity( supportTicketDto.getUser() ) );
        supportTicket.setTitle( supportTicketDto.getTitle() );
        supportTicket.setDescription( supportTicketDto.getDescription() );
        supportTicket.setPriority( supportTicketDto.getPriority() );
        supportTicket.setStatus( supportTicketDto.getStatus() );

        return supportTicket;
    }

    @Override
    public SupportTicketDto toSupportTicketDto(SupportTicket supportTicket) {
        if ( supportTicket == null ) {
            return null;
        }

        User user = null;
        String title = null;
        String description = null;
        TicketPriority priority = null;
        TicketStatus status = null;

        user = userMapper.toEntity( supportTicket.getUser() );
        title = supportTicket.getTitle();
        description = supportTicket.getDescription();
        priority = supportTicket.getPriority();
        status = supportTicket.getStatus();

        SupportTicketDto supportTicketDto = new SupportTicketDto( user, title, description, priority, status );

        return supportTicketDto;
    }

    @Override
    public SupportTicket updateWithNull(SupportTicketDto supportTicketDto, SupportTicket supportTicket) {
        if ( supportTicketDto == null ) {
            return supportTicket;
        }

        supportTicket.setUser( userMapper.toEntity( supportTicketDto.getUser() ) );
        supportTicket.setTitle( supportTicketDto.getTitle() );
        supportTicket.setDescription( supportTicketDto.getDescription() );
        supportTicket.setPriority( supportTicketDto.getPriority() );
        supportTicket.setStatus( supportTicketDto.getStatus() );

        return supportTicket;
    }
}
