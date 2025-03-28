package com.example.project_assouy.repo;

import com.example.project_assouy.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByLoginAndPasswordHash(String login, String passwordHash);

    boolean existsUserByLoginAndPasswordHash(@Size(max = 50) @NotNull String login, @Size(max = 255) @NotNull String passwordHash);

    boolean existsUserByLogin(String login);

    User findUserByLogin(String login);

    boolean existsByLogin(@NotNull @Size(max = 50) String userIdLogin);
}