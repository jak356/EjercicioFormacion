package com.example.EJS31.estudiante.infrastructure.dto;

import com.example.EJS31.estudiante.domain.StudentEntity;
import com.example.EJS31.estudios.domain.EstudiosEntity;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDTO {
  private String id_student;
  private String person_id;
  private Integer num_hours_week;
  private String comments;
  public String profesor_id;
  private String branch;
  private List<EstudiosEntity> estudios;
  public StudentInputDTO(StudentEntity student)
  {
    setId_student(student.getId_student());
    setNum_hours_week(student.getNum_hours_week());
    setComments(student.getComents());
    setBranch(student.getBranch());
  }


}
