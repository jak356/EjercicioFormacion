package com.example.BS9.repository;

import com.example.BS9.domain.StudentEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEstudianteRepo extends JpaRepository<StudentEntity, String> {

  @Query(value = "SELECT * FROM Estudiantes WHERE person_id=?1", nativeQuery = true)
  Optional<StudentEntity> findByPersonId(String id_persona);
}


