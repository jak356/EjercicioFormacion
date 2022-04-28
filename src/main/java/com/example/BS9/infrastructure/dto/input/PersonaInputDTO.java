package com.example.BS9.infrastructure.dto.input;

import com.example.BS9.domain.PersonaEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PersonaInputDTO {
  private String id_persona;
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
  public PersonaInputDTO(PersonaEntity persona)
  {
    setId_persona(persona.getId_persona());
    setCity(persona.getCity());
    setActive(persona.getActive());
    setCompany_email(persona.getCompany_email());
    setCreated_date(persona.getCreated_date());
    setPassword(persona.getPassword());
    setName(persona.getName());
    setSurname(persona.getSurname());
    setUsuario(persona.getUsuario());
    setTermination_date(persona.getTermination_date());
    setImagen_url(persona.getImagen_url());
    setPersonal_email(persona.getPersonal_email());
  }

}