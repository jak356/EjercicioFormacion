package com.example.BS9.repository;

import com.example.BS9.domain.EstudiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudiosRepo extends JpaRepository<EstudiosEntity, String> {

}
