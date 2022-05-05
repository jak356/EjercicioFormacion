package com.example.Ej2Record.Persona;

import com.example.Ej2Record.domain.PersonaEntity;
import com.example.Ej2Record.dto.PersonaDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaEntity, Integer> {
   @Query(value = "SELECT * FROM Persona WHERE name = ?", nativeQuery = true)
   List<PersonaDTO> findByName(String name);
   void deleteById(Integer id);
}
