package com.example.DBA2.Persona;

import com.example.DBA2.domain.PersonaEntity;
import com.example.DBA2.dto.PersonaInputDTO;
import com.example.DBA2.dto.PersonaOutPutDTO;
import java.util.List;

public interface IPersona {
   void addPersona(PersonaInputDTO personaDTO) throws Exception;
   PersonaOutPutDTO findById(Integer id) throws Exception;
   List<PersonaEntity> findAll();
   void deleteById(Integer id ) throws Exception;
}
