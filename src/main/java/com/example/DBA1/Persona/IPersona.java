package com.example.DBA1.Persona;

import com.example.DBA1.domain.PersonaEntity;
import com.example.DBA1.dto.PersonaInputDTO;
import com.example.DBA1.dto.PersonaOutPutDTO;

import java.util.HashMap;
import java.util.List;

public interface IPersona {
   PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws Exception;
   PersonaOutPutDTO findById(Integer id) throws Exception;
   List<PersonaOutPutDTO> findByName(String name) throws Exception;
   List<PersonaEntity> findAll();
   String deleteById(Integer id ) throws Exception;
   List<PersonaEntity> findByCondiciones(HashMap<String,Object> conditions);
}
