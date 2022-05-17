package com.example.EJS31.estudiante.infrastructure.repository;

import com.example.EJS31.estudiante.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IEstudianteRepo extends JpaRepository<StudentEntity, String> {

  @Query(value = "SELECT * FROM Estudiantes WHERE person_id=?1", nativeQuery = true)
  Optional<StudentEntity> findByPersonId(String id_persona);
}


