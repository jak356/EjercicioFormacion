package com.example.EJS31.persona.domain.dto;

import com.example.EJS31.estudios.domain.EstudiosEntity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosOutputDTO {
  private String id_study;
  private List<EstudiosEntity> studentList;
  private String asignatura;
  private String comment;
  private Date initial_date;
  private Date finish_date;

  public EstudiosOutputDTO(EstudiosEntity estudios)
  {
    setId_study(estudios.getId_study());
    setAsignatura(estudios.getAsignatura());
    setInitial_date(estudios.getInitial_date());
    setFinish_date(estudios.getFinish_date());
  }

}
