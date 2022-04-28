package com.example.BS9.infrastructure.dto.output;

import com.example.BS9.domain.EstudiosEntity;
import com.example.BS9.domain.ProfesorEntity;
import com.example.BS9.domain.StudentEntity;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentSimpleDTO {
  private String id_student;
  private Integer num_hours_week;
  private ProfesorEntity profesor;
  private String comments;
  private String branch;
  private List<EstudiosEntity> estudios;
  public StudentSimpleDTO(StudentEntity student) {
    setId_student(student.getId_student());

    setNum_hours_week(student.getNum_hours_week());
    setProfesor(student.getProfesor());
    setComments(student.getComents());
    setBranch(student.getBranch());
    setEstudios(student.getEstudios());
  }


}
