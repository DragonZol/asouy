package com.example.project_assouy.mapper;

import com.example.project_assouy.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T12:08:10+0800",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.42.0.v20250325-2231, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setCreateTs( user.getCreateTs() );
        user1.setCreatedBy( user.getCreatedBy() );
        user1.setDeleteTs( user.getDeleteTs() );
        user1.setDeletedBy( user.getDeletedBy() );
        user1.setUpdateTs( user.getUpdateTs() );
        user1.setUpdatedBy( user.getUpdatedBy() );
        user1.setId( user.getId() );
        user1.setIsActive( user.getIsActive() );
        user1.setLogin( user.getLogin() );
        user1.setPasswordHash( user.getPasswordHash() );
        user1.setPhoneNumber( user.getPhoneNumber() );
        user1.setRole( user.getRole() );

        return user1;
    }
}
