package com.example.EJS31.profesor.infrastructure.repository;

import com.example.EJS31.profesor.domain.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IProfesorRepo extends JpaRepository<ProfesorEntity, String> {
  @Query(value = "SELECT * FROM Profesor WHERE person_id = ?1", nativeQuery = true)
  Optional<ProfesorEntity> findByPersonID(String id);

}
