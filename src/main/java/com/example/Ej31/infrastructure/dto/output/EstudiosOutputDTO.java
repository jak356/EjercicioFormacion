package com.example.Ej31.infrastructure.dto.output;

import com.example.Ej31.domain.EstudiosEntity;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
