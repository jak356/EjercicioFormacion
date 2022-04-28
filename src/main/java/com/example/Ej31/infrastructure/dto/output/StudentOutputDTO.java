package com.example.Ej31.infrastructure.dto.output;

import com.example.Ej31.domain.EstudiosEntity;
import com.example.Ej31.domain.PersonaEntity;
import com.example.Ej31.domain.ProfesorEntity;
import com.example.Ej31.domain.StudentEntity;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentOutputDTO {
  private String id_student;
  private PersonaEntity persona;
  private Integer num_hours_week;
  private ProfesorEntity profesor;
  private String comments;
  private String branch;
  private List<EstudiosEntity> estudios;
  public StudentOutputDTO(StudentEntity student)
  {
    setId_student(student.getId_student());
    setPersona(student.getPersona());
    setNum_hours_week(student.getNum_hours_week());
    setProfesor(student.getProfesor());
    setComments(student.getComents());
    setBranch(student.getBranch());
    setEstudios(student.getEstudios());


  }


}