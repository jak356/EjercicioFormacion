package com.example.Ej31.domain;

import com.example.Ej31.StringPrefixedSequenceIdGenerator;
import com.example.Ej31.infrastructure.dto.input.EstudiosInputDTO;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "estudios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudiosEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudio_seq")
  @GenericGenerator(
      name = "estudio_seq",
      strategy = "com.example.Ej31.StringPrefixedSequenceIdGenerator",
      parameters = {
          @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
          @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EST"),
          @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
      })
  String id_study;

  @ManyToMany
  @JoinTable(
      name = "student_studies",
      joinColumns = {@JoinColumn(name = "id_study")},
      inverseJoinColumns = {@JoinColumn(name = "id_student")})
  List<StudentEntity> studentList;

  @Column(name = "asignatura")
  String asignatura;

  @Column(name = "comentarios")
  String comment;

  @Column(name = "initial_date")
  Date initial_date;

  @Column(name = "finish_date")
  Date finish_date;


  public EstudiosEntity(EstudiosInputDTO estudiosInputDTO) {
    setId_study(estudiosInputDTO.getId_study());
    setAsignatura((estudiosInputDTO.getAsignatura()));
    setComment(estudiosInputDTO.getComment());
    setInitial_date(estudiosInputDTO.getInitial_date());
    setFinish_date(estudiosInputDTO.getFinish_date());
  }


}
