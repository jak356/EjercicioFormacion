package com.example.Ej31.infrastructure.dto.input;

import com.example.Ej31.domain.EstudiosEntity;
import com.example.Ej31.domain.StudentEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
