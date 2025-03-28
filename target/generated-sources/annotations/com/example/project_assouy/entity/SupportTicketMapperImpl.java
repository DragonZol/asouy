package com.example.project_assouy.entity;

import com.example.project_assouy.dto.SupportTicketDto;
import com.example.project_assouy.enums.TicketPriority;
import com.example.project_assouy.enums.TicketStatus;
import com.example.project_assouy.mapper.UserMapper;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T12:08:10+0800",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.42.0.v20250325-2231, environment: Java 21.0.6 (Eclipse Adoptium)"
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
        supportTicket1.setDeleteTs( supportTicket.getDeleteTs() );
        supportTicket1.setDeletedBy( supportTicket.getDeletedBy() );
        supportTicket1.setUpdateTs( supportTicket.getUpdateTs() );
        supportTicket1.setUpdatedBy( supportTicket.getUpdatedBy() );
        supportTicket1.setId( supportTicket.getId() );
        supportTicket1.setDescription( supportTicket.getDescription() );
        supportTicket1.setPriority( supportTicket.getPriority() );
        supportTicket1.setStatus( supportTicket.getStatus() );
        supportTicket1.setTitle( supportTicket.getTitle() );
        supportTicket1.setUser( userMapper.toEntity( supportTicket.getUser() ) );

        return supportTicket1;
    }

    @Override
    public SupportTicket toEntity(SupportTicketDto supportTicketDto) {
        if ( supportTicketDto == null ) {
            return null;
        }

        SupportTicket supportTicket = new SupportTicket();

        supportTicket.setUser( supportTicketDtoToUser( supportTicketDto ) );
        supportTicket.setId( supportTicketDto.getId() );
        supportTicket.setDescription( supportTicketDto.getDescription() );
        supportTicket.setPriority( supportTicketDto.getPriority() );
        supportTicket.setStatus( supportTicketDto.getStatus() );
        supportTicket.setTitle( supportTicketDto.getTitle() );

        return supportTicket;
    }

    @Override
    public SupportTicketDto toSupportTicketDto(SupportTicket supportTicket) {
        if ( supportTicket == null ) {
            return null;
        }

        String userLogin = null;
        UUID id = null;
        User user = null;
        String title = null;
        String description = null;
        TicketPriority priority = null;
        TicketStatus status = null;

        userLogin = supportTicketUserLogin( supportTicket );
        id = supportTicket.getId();
        user = userMapper.toEntity( supportTicket.getUser() );
        title = supportTicket.getTitle();
        description = supportTicket.getDescription();
        priority = supportTicket.getPriority();
        status = supportTicket.getStatus();

        SupportTicketDto supportTicketDto = new SupportTicketDto( user, id, userLogin, title, description, priority, status );

        return supportTicketDto;
    }

    @Override
    public SupportTicket updateWithNull(SupportTicketDto supportTicketDto, SupportTicket supportTicket) {
        if ( supportTicketDto == null ) {
            return supportTicket;
        }

        supportTicket.setId( supportTicketDto.getId() );
        supportTicket.setDescription( supportTicketDto.getDescription() );
        supportTicket.setPriority( supportTicketDto.getPriority() );
        supportTicket.setStatus( supportTicketDto.getStatus() );
        supportTicket.setTitle( supportTicketDto.getTitle() );
        supportTicket.setUser( userMapper.toEntity( supportTicketDto.getUser() ) );

        return supportTicket;
    }

    protected User supportTicketDtoToUser(SupportTicketDto supportTicketDto) {
        if ( supportTicketDto == null ) {
            return null;
        }

        User user = new User();

        user.setLogin( supportTicketDto.getUserLogin() );

        return user;
    }

    private String supportTicketUserLogin(SupportTicket supportTicket) {
        User user = supportTicket.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getLogin();
    }
}
