package com.example.Ej2CRUD.Persona;

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
  public PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws Exception {
    if(personaDTO.getUsuario().length() < 6 || personaDTO.getUsuario().length() > 10)
    { throw new Exception("El usuario debe tener entre 6 y 10 caracteres");
    } else{
      PersonaEntity personaEntity = new PersonaEntity(personaDTO);
      personaRepo.save(personaEntity);
      PersonaOutPutDTO saveOutPutDTO = new PersonaOutPutDTO(personaEntity);
      return saveOutPutDTO;
    }
  }

  @Override
  public PersonaOutPutDTO findById(Integer id) throws Exception {
     PersonaEntity personById = personaRepo.findById(id).orElseThrow(() -> new Exception("Id no econtrado"));
     return new PersonaOutPutDTO(personById);
    }

  @Override
  public List<PersonaOutPutDTO> findByName(String name) throws Exception {
    try {
      List<PersonaOutPutDTO> personaEntities = personaRepo.findByName(name);
      return personaEntities;
    } catch(Exception e) {
      throw new Exception("No se ha podido encontrar el usuario");
    }
  }

  @Override
  public List<PersonaEntity> findAll() { return personaRepo.findAll();}

  @Override
  public String deleteById(Integer id) throws Exception {
    personaRepo.findById(id).orElseThrow(() -> new Exception("NO se ha encomtrado a la persona cuyo id es: " + id));
    personaRepo.deleteById(id);
    return "La persona cuyo es id es " + id + " ha sido borrada";

  }
}
