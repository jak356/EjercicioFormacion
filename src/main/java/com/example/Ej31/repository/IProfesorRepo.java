package com.example.Ej31.repository;

import com.example.Ej31.domain.ProfesorEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProfesorRepo extends JpaRepository<ProfesorEntity, String> {
  @Query(value = "SELECT * FROM Profesor WHERE person_id = ?1", nativeQuery = true)
  Optional<ProfesorEntity> findByPersonID(String id);

}
