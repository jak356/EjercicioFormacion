package com.example.BS9.Services;

import com.example.BS9.ErrorHandlers.NotFoundException;
import com.example.BS9.ErrorHandlers.UnprocesableException;
import com.example.BS9.Services.interfaces.IPersona;
import com.example.BS9.domain.PersonaEntity;
import com.example.BS9.infrastructure.dto.input.PersonaInputDTO;
import com.example.BS9.infrastructure.dto.output.PersonaOutPutDTO;
import com.example.BS9.repository.IPersonaRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersona {

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
