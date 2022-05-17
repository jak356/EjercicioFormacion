package com.example.EJS31.estudios.domain;

import com.example.EJS31.estudiante.domain.StudentEntity;
import com.example.EJS31.persona.domain.dto.EstudiosInputDTO;
import com.example.EJS31.shared.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
            strategy = "com.example.EJS31.StringPrefixedSequenceIdGenerator",
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