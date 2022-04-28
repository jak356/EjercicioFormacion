package com.example.Ej31.infrastructure.dto.input;

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
public class EstudiosInputDTO {
  private String id_study;
  private List<EstudiosEntity> studentList;
  private String asignatura;
  private String comment;
  private Date initial_date;
  private Date finish_date;

}
