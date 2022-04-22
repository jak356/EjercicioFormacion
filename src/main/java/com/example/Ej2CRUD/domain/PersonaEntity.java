package com.example.Ej2CRUD.domain;
import com.example.Ej2CRUD.infrastructure.dto.PersonaInputDTO;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class PersonaEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  Integer id_persona;

  @NonNull
  @Column(name="usuario")
  String usuario;

  @NonNull
  @Column(name="password")
  String password;

  @NonNull
  @Column(name="name")
  String name;

  @NonNull
  @Column(name="surname")
  String surname;

  @NonNull
  @Column(name="company_email")
  String company_email;

  @NonNull
  @Column(name="personal_email")
  String personal_email;

  @NonNull
  @Column(name="city")
  String city;

  @NonNull
  @Column(name="active")
  Boolean active;

  @NonNull
  @Column(name="created_date")
  Date created_date;

  @Column(name="imagen_url")
  String imagen_url;

  @Column(name="termination_date")
  Date termination_date;

  public PersonaEntity() {
  }
  public PersonaEntity(PersonaInputDTO personaDTO) {
    if(personaDTO == null) return;
    setId_persona(personaDTO.getId_persona());
    setCity(personaDTO.getCity());
    setActive(personaDTO.getActive());
    setCompany_email(personaDTO.getCompany_email());
    setCreated_date(personaDTO.getCreated_date());
    setPassword(personaDTO.getPassword());
    setName(personaDTO.getName());
    setSurname(personaDTO.getSurname());
    setUsuario(personaDTO.getUsuario());
    setTermination_date(personaDTO.getTermination_date());
    setImagen_url(personaDTO.getImagen_url());
    setPersonal_email(personaDTO.getPersonal_email());
  }

}
