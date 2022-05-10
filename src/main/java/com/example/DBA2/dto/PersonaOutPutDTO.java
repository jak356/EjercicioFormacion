package com.example.DBA2.dto;

import com.example.DBA2.domain.PersonaEntity;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PersonaOutPutDTO {
  private int id_persona;
  private String usuario;
  private String password;
  private String name;
  private String surname;
  private String company_email;
  private String personal_email;
  private String city;
  private Boolean active;
  private Date created_date;
  private String imagen_url;
  private Date termination_date;

  public PersonaOutPutDTO(PersonaEntity personaEntity)
  {
    setId_persona(personaEntity.getId_persona());
    setCity(personaEntity.getCity());
    setActive(personaEntity.getActive());
    setCompany_email(personaEntity.getCompany_email());
    setCreated_date(personaEntity.getCreated_date());
    setPassword(personaEntity.getPassword());
    setName(personaEntity.getName());
    setSurname(personaEntity.getSurname());
    setUsuario(personaEntity.getUsuario());
    setTermination_date(personaEntity.getTermination_date());
    setImagen_url(personaEntity.getImagen_url());
    setPersonal_email(personaEntity.getPersonal_email());



  }
  public PersonaOutPutDTO() {}


}

