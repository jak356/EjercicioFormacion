package com.example.Ej31.infrastructure.dto.output;

import com.example.Ej31.domain.PersonaEntity;
import com.example.Ej31.domain.ProfesorEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProfesorOutputDTO {
  private String id_profesor;
  private PersonaEntity persona;
  private String coments;
  private String branch;
  public ProfesorOutputDTO(ProfesorEntity profesor)
  {
    setId_profesor(profesor.getId_profesor());
    setPersona(profesor.getPersona());
    setComents(profesor.getComents());
    setBranch(profesor.getBranch());

  }

}
