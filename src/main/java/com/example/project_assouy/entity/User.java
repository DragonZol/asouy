package com.example.project_assouy.entity;

import com.example.project_assouy.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends StandartEntityId{

    @Size(max = 15)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_id")
    private Role role;

    @Column(name = "is_active")
    private Boolean isActive;

}