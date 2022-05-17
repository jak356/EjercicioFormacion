package com.example.EJS31.estudiante.infrastructure.dto;

import com.example.EJS31.estudiante.domain.StudentEntity;
import com.example.EJS31.estudios.domain.EstudiosEntity;
import com.example.EJS31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
