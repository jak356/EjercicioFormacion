package com.example.Ej2CRUD.Persona;

import com.example.Ej2CRUD.domain.PersonaEntity;
import com.example.Ej2CRUD.infrastructure.dto.PersonaOutPutDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaEntity, Integer> {
   List<PersonaOutPutDTO> findByName(String name);
   void deleteById(Integer id);
}
