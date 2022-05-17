package com.example.EJS31.persona.application;

import com.example.EJS31.persona.domain.PersonaEntity;
import com.example.EJS31.persona.infrastructure.dto.PersonaInputDTO;
import com.example.EJS31.persona.infrastructure.dto.PersonaOutPutDTO;
import com.example.EJS31.persona.infrastructure.repository.IPersonaRepo;
import com.example.EJS31.shared.error.NotFoundException;
import com.example.EJS31.shared.error.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService implements IPersonaService {

  @Autowired
  private IPersonaRepo personaRepo;


  @Override
  public PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws UnprocesableException {
    if (personaDTO.getUsuario().length() < 6 || personaDTO.getUsuario().length() > 10) {
      throw new UnprocesableException("El usuario debe tener entre 6 y 10 caracteres");
    } else {
      PersonaEntity personaEntity = new PersonaEntity(personaDTO);
      personaRepo.save(personaEntity);
      return new PersonaOutPutDTO(personaEntity);
    }
  }

  @Override
  public PersonaOutPutDTO findById(String id) throws NotFoundException {
    PersonaEntity personById = personaRepo.findById(id).orElseThrow(
        () -> new NotFoundException("No se ha encomtrado a la persona cuyo id es: " + id));
    return new PersonaOutPutDTO(personById);
  }

  @Override
  public List<PersonaOutPutDTO> findByName(String name) throws NotFoundException {
    try {
      return personaRepo.findByName(name);
    } catch (Exception e) {
      throw new NotFoundException("No se ha podido encontrar el usuario " + name);
    }
  }

  @Override
  public List<PersonaEntity> findAll() {
    return personaRepo.findAll();
  }

  @Override
  public String deleteById(String id) throws NotFoundException {
    personaRepo.findById(id).orElseThrow(
        () -> new NotFoundException("No se ha encomtrado a la persona cuyo id es: " + id));
    personaRepo.deleteById(id);
    return "La persona cuyo  id es " + id + " ha sido borrada";

  }
}
