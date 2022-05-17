package com.example.EJS31.estudios.infrastructure.repository;

import com.example.EJS31.estudios.domain.EstudiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudiosRepo extends JpaRepository<EstudiosEntity, String> {

}
