package com.example.EJS31.persona.application;

import com.example.EJS31.persona.domain.PersonaEntity;
import com.example.EJS31.persona.infrastructure.dto.PersonaInputDTO;
import com.example.EJS31.persona.infrastructure.dto.PersonaOutPutDTO;
import com.example.EJS31.shared.error.NotFoundException;
import com.example.EJS31.shared.error.UnprocesableException;

import java.util.List;

public interface IPersonaService {

  PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws UnprocesableException;

  PersonaOutPutDTO findById(String id) throws NotFoundException;

  List<PersonaOutPutDTO> findByName(String name) throws NotFoundException;

  List<PersonaEntity> findAll();

  String deleteById(String id) throws NotFoundException;


}
