package com.example.BackEmpresa.user.infrastructure.repository;

import com.example.BackEmpresa.user.domain.UserEntity;
import org.hibernate.annotations.NotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @NotFound
    UserEntity findByEmail(String email);
}
