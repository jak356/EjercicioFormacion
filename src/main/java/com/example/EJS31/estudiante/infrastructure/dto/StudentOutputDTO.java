package com.example.EJS31.estudiante.infrastructure.dto;

import com.example.EJS31.estudiante.domain.StudentEntity;
import com.example.EJS31.estudios.domain.EstudiosEntity;
import com.example.EJS31.persona.domain.PersonaEntity;
import com.example.EJS31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
