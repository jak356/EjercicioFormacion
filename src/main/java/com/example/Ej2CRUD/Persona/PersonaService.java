package com.example.Ej2CRUD.Persona;

import com.example.Ej2CRUD.ErrorHandlers.NotFoundException;
import com.example.Ej2CRUD.ErrorHandlers.UnprocesableException;
import com.example.Ej2CRUD.domain.PersonaEntity;
import com.example.Ej2CRUD.infrastructure.dto.PersonaInputDTO;
import com.example.Ej2CRUD.infrastructure.dto.PersonaOutPutDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersona {

@Autowired
  private IPersonaRepo personaRepo;


  @Override
  public PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws UnprocesableException {
    if(personaDTO.getUsuario().length() < 6 || personaDTO.getUsuario().length() > 10)
    { throw new UnprocesableException("El usuario debe tener entre 6 y 10 caracteres");
    } else{
      PersonaEntity personaEntity = new PersonaEntity(personaDTO);
      personaRepo.save(personaEntity);
      PersonaOutPutDTO saveOutPutDTO = new PersonaOutPutDTO(personaEntity);
      return saveOutPutDTO;
    }
  }

  @Override
  public PersonaOutPutDTO findById(Integer id) throws NotFoundException {
     PersonaEntity personById = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encomtrado a la persona cuyo id es: "  + id));
     return new PersonaOutPutDTO(personById);
    }

  @Override
  public List<PersonaOutPutDTO> findByName(String name) throws NotFoundException {
    try {
      List<PersonaOutPutDTO> personaEntities = personaRepo.findByName(name);
      return personaEntities;
    } catch(Exception e) {
      throw new NotFoundException("No se ha podido encontrar el usuario " + name);
    }
  }

  @Override
  public List<PersonaEntity> findAll() { return personaRepo.findAll();}

  @Override
  public String deleteById(Integer id) throws NotFoundException {
    personaRepo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encomtrado a la persona cuyo id es: " + id));
    personaRepo.deleteById(id);
    return "La persona cuyo es id es " + id + " ha sido borrada";

  }
}
