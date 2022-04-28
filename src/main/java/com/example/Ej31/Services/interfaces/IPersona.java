package com.example.Ej31.Services.interfaces;

import com.example.Ej31.domain.PersonaEntity;
import com.example.Ej31.infrastructure.dto.input.PersonaInputDTO;
import com.example.Ej31.infrastructure.dto.output.PersonaOutPutDTO;
import com.example.Ej31.infrastructure.dto.output.ProfesorOutputDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

public interface IPersona {

  PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws Exception;

  PersonaOutPutDTO findById(String id) throws Exception;

  List<PersonaOutPutDTO> findByName(String name) throws Exception;

  List<PersonaEntity> findAll();

  String deleteById(String id) throws Exception;


}
