package com.example.Ej2CRUD.Persona;

import com.example.Ej2CRUD.domain.PersonaEntity;
import com.example.Ej2CRUD.infrastructure.dto.PersonaInputDTO;
import com.example.Ej2CRUD.infrastructure.dto.PersonaOutPutDTO;
import java.util.List;

public interface IPersona {
   PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws Exception;
   PersonaOutPutDTO findById(Integer id) throws Exception;
   List<PersonaOutPutDTO> findByName(String name) throws Exception;
   List<PersonaEntity> findAll();
   String deleteById(Integer id ) throws Exception;
}
