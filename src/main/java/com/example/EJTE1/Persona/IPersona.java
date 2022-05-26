package com.example.EJTE1.Persona;

import com.example.EJTE1.domain.PersonaEntity;
import com.example.EJTE1.dto.PersonaInputDTO;
import com.example.EJTE1.dto.PersonaOutPutDTO;

import java.util.List;

public interface IPersona {
    PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws Exception;

    PersonaOutPutDTO findById(Integer id) throws Exception;

    List<PersonaOutPutDTO> findByName(String name) throws Exception;

    List<PersonaOutPutDTO> findAll();

    PersonaEntity deleteById(Integer id) throws Exception;
}
