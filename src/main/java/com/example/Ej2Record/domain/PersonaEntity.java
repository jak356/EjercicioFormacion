package com.example.Ej2Record.domain;
import com.example.Ej2Record.dto.PersonaDTO;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  public PersonaEntity(PersonaDTO personaDTO) {
    if(personaDTO == null) return;
    setId_persona(personaDTO.id_persona());
    setCity(personaDTO.city());
    setActive(personaDTO.active());
    setCompany_email(personaDTO.company_email());
    setCreated_date(personaDTO.created_date());
    setPassword(personaDTO.password());
    setName(personaDTO.name());
    setSurname(personaDTO.surname());
    setUsuario(personaDTO.usuario());
    setTermination_date(personaDTO.termination_date());
    setImagen_url(personaDTO.imagen_url());
    setPersonal_email(personaDTO.personal_email());
  }

}
