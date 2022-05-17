package com.example.EJS31.persona.infrastructure.dto;

import com.example.EJS31.persona.domain.PersonaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
  private String admin;
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
    setAdmin(persona.getAdmin());
  }

}