package com.example.Ej2Record.Persona;

import com.example.Ej2Record.ErrorHandlers.NotFoundException;
import com.example.Ej2Record.ErrorHandlers.UnprocesableException;
import com.example.Ej2Record.domain.PersonaEntity;
import com.example.Ej2Record.dto.PersonaDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersona {

@Autowired
  private IPersonaRepo personaRepo;


  @Override
  public PersonaDTO addPersona(PersonaDTO personaDTO) throws UnprocesableException {
    if(personaDTO.usuario().length() < 6 || personaDTO.usuario().length() > 10)
    { throw new UnprocesableException("El usuario debe tener entre 6 y 10 caracteres");
    } else{
      PersonaEntity personaEntity = new PersonaEntity(personaDTO);
      personaRepo.save(personaEntity);
      PersonaDTO saveOutPutDTO = new PersonaDTO(personaEntity.getId_persona(), personaEntity.getUsuario(),personaEntity.getPassword(),personaEntity.getName(),personaEntity.getSurname(),personaEntity.getCompany_email(),personaEntity.getPersonal_email(),personaEntity.getCity(),personaEntity.getActive(),personaEntity.getCreated_date(),personaEntity.getImagen_url(),personaEntity.getTermination_date());
      return saveOutPutDTO;
    }
  }

  @Override
  public PersonaDTO findById(Integer id) throws NotFoundException {
    PersonaEntity personById = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encomtrado a la persona cuyo id es: "  + id));
     return new PersonaDTO(personById.getId_persona(), personById.getUsuario(),personById.getPassword(),personById.getName(),personById.getSurname(),personById.getCompany_email(),personById.getPersonal_email(),personById.getCity(),personById.getActive(),personById.getCreated_date(),personById.getImagen_url(),personById.getTermination_date());
    }

  @Override
  public List<PersonaDTO> findByName(String name) throws NotFoundException {
    try {
      List<PersonaDTO> personaEntities = personaRepo.findByName(name);
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
    return "La persona cuyo  id es " + id + " ha sido borrada";

  }
}
