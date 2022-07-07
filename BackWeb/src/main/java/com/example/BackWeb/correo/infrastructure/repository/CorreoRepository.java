package com.example.BackWeb.correo.infrastructure.repository;


import com.example.BackWeb.correo.domain.CorreoEntity;
import org.hibernate.annotations.NotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface CorreoRepository extends JpaRepository<CorreoEntity, UUID> {
    @NotFound
    CorreoEntity findByEmail(String email);
}
