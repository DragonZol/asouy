package com.example.project_assouy.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract public class AuditedEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "CREATE_TS", updatable = false)
    protected LocalDateTime createTs ;

    @CreatedBy
    @Column(name = "CREATED_BY", length = 50, updatable = false)
    protected String createdBy;

    @LastModifiedDate
    @Column(name = "UPDATE_TS")
    protected LocalDateTime updateTs ;

    @LastModifiedBy
    @Column(name = "UPDATED_BY", length = 50)
    protected String updatedBy;

    @Column(name = "DELETE_TS")
    protected LocalDateTime deleteTs;

    @Column(name = "DELETED_BY", length = 50)
    protected String deletedBy;
}