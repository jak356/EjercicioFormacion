package com.example.BS9.domain;

import com.example.BS9.StringPrefixedSequenceIdGenerator;
import com.example.BS9.infrastructure.dto.input.ProfesorInputDTO;
import com.example.BS9.infrastructure.dto.output.ProfesorOutputDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Data
@Table(name = "profesor")
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesor_seq")
  @GenericGenerator(
      name = "profesor_seq",
      strategy = "com.example.BS9.StringPrefixedSequenceIdGenerator",
      parameters = {
          @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
          @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO"),
          @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
      })
  private String id_profesor;

  @OneToOne
  @JoinColumn(name = "id_persona")
  private PersonaEntity persona;

  @Column(name = "comentarios")
  private String coments;

  @Column(name = "rama")
  private String branch;

  public ProfesorEntity(ProfesorInputDTO profesorInputDTO) {
    setId_profesor(profesorInputDTO.getId_profesor());
    setPersona(profesorInputDTO.getPersona());
    setComents(profesorInputDTO.getComents());
    setBranch(profesorInputDTO.getBranch());

  }

  public ProfesorEntity(ProfesorOutputDTO profesorOutputDTO) {
    setId_profesor(profesorOutputDTO.getId_profesor());
    setPersona(profesorOutputDTO.getPersona());
    setComents(profesorOutputDTO.getComents());
    setBranch(profesorOutputDTO.getBranch());

  }

}
