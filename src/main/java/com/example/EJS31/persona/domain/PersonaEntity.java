package com.example.EJS31.persona.domain;

import com.example.EJS31.persona.infrastructure.dto.PersonaInputDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import com.example.EJS31.StringPrefixedSequenceIdGenerator;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="Personas")
@AllArgsConstructor
@NoArgsConstructor
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_seq")
    @GenericGenerator(
            name = "persona_seq",
            strategy = "com.example.EJS31.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PER"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })

    private String id_persona;

    @NonNull
    @Column(name = "usuario")
    private String usuario;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "surname")
    private String surname;

    @NonNull
    @Column(name = "company_email")
    private String company_email;

    @NonNull
    @Column(name = "personal_email")
    private String personal_email;

    @NonNull
    @Column(name = "city")
    private String city;

    @NonNull
    @Column(name = "active")
    private Boolean active;

    @NonNull
    @Column(name = "created_date")
    private Date created_date;

    @Column(name = "imagen_url")
    private String imagen_url;

    @Column(name = "termination_date")
    private Date termination_date;

    @NonNull
    @Column(name = "admin")
    private String admin;



    public PersonaEntity(PersonaInputDTO personaDTO) {
        if (personaDTO == null) {
            return;
        }
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
        setAdmin(personaDTO.getAdmin());
    }

}
