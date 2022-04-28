package com.example.BS9.domain;

import com.example.BS9.StringPrefixedSequenceIdGenerator;
import com.example.BS9.infrastructure.dto.input.StudentInputDTO;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Data
@Table(name = "estudiantes")
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
  @GenericGenerator(
      name = "student_seq",
      strategy = "com.example.BS9.StringPrefixedSequenceIdGenerator",
      parameters = {
          @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
          @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "STU"),
          @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
      })
 private String id_student;

  @OneToOne
  @JoinColumn(name = "id_persona")
  private PersonaEntity persona;

  @Column(name = "horas_por_semana")
  private Integer num_hours_week;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_profesor")
  private ProfesorEntity profesor;

  @Column(name = "comentarios")
  private String coments;

  @Column(name = "rama")
  private String branch;

  @ManyToMany(mappedBy = "studentList")
  private List<EstudiosEntity> estudios;

  public StudentEntity(StudentInputDTO studentInputDTO) {
    setId_student(studentInputDTO.getId_student());
    setNum_hours_week(studentInputDTO.getNum_hours_week());
    setComents(studentInputDTO.getComments());
    setBranch(studentInputDTO.getBranch());
  }


}
