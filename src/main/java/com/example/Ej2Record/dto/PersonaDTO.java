package com.example.Ej2Record.dto;


import com.example.Ej2Record.domain.PersonaEntity;
import java.util.Date;
import lombok.Data;

public record PersonaDTO(Integer id_persona,String usuario,String password,String name,String surname,String company_email, String personal_email, String city, Boolean active, Date created_date, String imagen_url, Date termination_date ) {

}