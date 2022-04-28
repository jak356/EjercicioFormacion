package com.example.BS9.Services.interfaces;

import com.example.BS9.domain.PersonaEntity;
import com.example.BS9.infrastructure.dto.input.PersonaInputDTO;
import com.example.BS9.infrastructure.dto.output.PersonaOutPutDTO;
import java.util.List;

public interface IPersona {

  PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws Exception;

  PersonaOutPutDTO findById(String id) throws Exception;

  List<PersonaOutPutDTO> findByName(String name) throws Exception;

  List<PersonaEntity> findAll();

  String deleteById(String id) throws Exception;


}
