package com.example.EJS31.profesor.infrastructure.dto;

import com.example.EJS31.persona.domain.PersonaEntity;
import com.example.EJS31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProfesorInputDTO {

  private String id_profesor;
  private PersonaEntity persona;
  private String coments;
  private String branch;
  public ProfesorInputDTO(ProfesorEntity profesor)
  {
    setId_profesor(profesor.getId_profesor());
    setPersona(profesor.getPersona());
    setComents(profesor.getComents());
    setBranch((profesor.getBranch()));

  }

}
