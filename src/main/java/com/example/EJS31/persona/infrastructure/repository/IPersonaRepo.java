package com.example.EJS31.persona.infrastructure.repository;

import com.example.EJS31.persona.domain.PersonaEntity;
import com.example.EJS31.persona.infrastructure.dto.PersonaOutPutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaEntity, String> {
  @Query(value = "SELECT * FROM Persona WHERE usuario = ?", nativeQuery = true)
  List<PersonaOutPutDTO> findByName(String name);

}
