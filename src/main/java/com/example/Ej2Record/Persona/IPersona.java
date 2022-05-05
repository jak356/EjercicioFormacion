package com.example.Ej2Record.Persona;

import com.example.Ej2Record.domain.PersonaEntity;
import com.example.Ej2Record.dto.PersonaDTO;
import java.util.List;

public interface IPersona {
   PersonaDTO addPersona(PersonaDTO personaDTO) throws Exception;
   PersonaDTO findById(Integer id) throws Exception;
   List<PersonaDTO> findByName(String name) throws Exception;
   List<PersonaEntity> findAll();
   String deleteById(Integer id ) throws Exception;
}
