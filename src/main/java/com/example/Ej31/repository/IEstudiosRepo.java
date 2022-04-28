package com.example.Ej31.repository;

import com.example.Ej31.domain.EstudiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudiosRepo extends JpaRepository<EstudiosEntity, String> {

}
