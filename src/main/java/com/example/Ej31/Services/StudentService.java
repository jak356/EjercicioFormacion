package com.example.Ej31.Services;

import com.example.Ej31.ErrorHandlers.NotFoundException;
import com.example.Ej31.ErrorHandlers.UnprocesableException;
import com.example.Ej31.Services.interfaces.IEstudiante;
import com.example.Ej31.domain.EstudiosEntity;
import com.example.Ej31.domain.PersonaEntity;
import com.example.Ej31.domain.ProfesorEntity;
import com.example.Ej31.infrastructure.dto.output.StudentSimpleDTO;
import com.example.Ej31.repository.IEstudianteRepo;
import com.example.Ej31.domain.StudentEntity;
import com.example.Ej31.infrastructure.dto.input.StudentInputDTO;
import com.example.Ej31.infrastructure.dto.output.StudentOutputDTO;
import com.example.Ej31.repository.IEstudiosRepo;
import com.example.Ej31.repository.IPersonaRepo;
import com.example.Ej31.repository.IProfesorRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IEstudiante {

  @Autowired
  IEstudianteRepo estudianteRepo;
  @Autowired
  IPersonaRepo personaRepo;
  @Autowired
  ProfesorService profesorService;
  @Autowired
  IProfesorRepo profesorRepo;
  @Autowired
  IEstudiosRepo estudiosRepo;

  @Override
  public ResponseEntity<?> addEstudiante(StudentInputDTO studentDTO) throws UnprocesableException {
    Optional<PersonaEntity> persona = personaRepo.findById(studentDTO.getPerson_id());

    //Comprobación lógica 1.- (la persona existe)
    if (persona.isPresent()) {
      StudentEntity student = new StudentEntity(studentDTO);
      student.setPersona(persona.get());
      //Comprobación lógica 2.- (el profesor existe)
      if (studentDTO.getProfesor_id() != null) {
        ProfesorEntity profesor = new ProfesorEntity(
            profesorService.findById(studentDTO.getProfesor_id()));
        student.setProfesor(profesor);
      } else {
        return new ResponseEntity<>("El profesor no existe", HttpStatus.NOT_FOUND);
      }
      //Comprobación lógica 3.- (la persona incluida no es un profesor)
      if (estudianteRepo.findByPersonId(persona.get().getId_persona()).isEmpty()
          && profesorRepo.findByPersonID(persona.get().getId_persona()).isEmpty()) {
        estudianteRepo.save(student);
        return new ResponseEntity<>("Estudiante añadido", HttpStatus.OK);
      } else {
        return new ResponseEntity<>(
            "La persona esta vinculada a un profesor, error al añadir estudiante",
            HttpStatus.BAD_REQUEST);
      }

    } else {
      return new ResponseEntity<>(" La persona no existe", HttpStatus.NOT_FOUND);
    }


  }

  @Override
  public StudentOutputDTO findById(String id) throws NotFoundException {
    StudentEntity studentById = estudianteRepo.findById(id).orElseThrow(
        () -> new NotFoundException("No se ha encomtrado al estudiante cuyo id es: " + id));
    return new StudentOutputDTO(studentById);
  }

  @Override
  public List<StudentEntity> findAll() {
    return estudianteRepo.findAll();
  }

  @Override
  public String deleteById(String id) throws NotFoundException {
    estudianteRepo.findById(id).orElseThrow(
        () -> new NotFoundException("No se ha econtrado al estudiante cuyo id es: " + id));
    estudianteRepo.deleteById(id);
    return "El estudiane cuyo id es " + id + " ha sido borrada";
  }

  @Override
  public StudentSimpleDTO findSimpleByID(String id) throws NotFoundException {
    StudentEntity studentById = estudianteRepo.findById(id).orElseThrow(
        () -> new NotFoundException("No se ha encomtrado al estudiante cuyo id es: " + id));
    return new StudentSimpleDTO(studentById);
  }

  @Override
  public StudentOutputDTO addEstudio(String studentID, List<String> EstudioIDList)
      throws UnprocesableException {
    Optional<StudentEntity> student = estudianteRepo.findById(studentID);
    //Comprobación lógica 1.-(Existe el estudiante)
    if (student.isEmpty()) {
      throw new UnprocesableException("No existe el estudiante");
    }
    List<EstudiosEntity> estudiosDeEstudiante = student.get().getEstudios();
    for (String id : EstudioIDList) {
      Optional<EstudiosEntity> estudiosEntity = estudiosRepo.findById(id);
      //Comprobación lógica 2.-(Si existe la id dentro de la lista asignar estudios al estudiante)
      if (estudiosEntity.isPresent()) {
        estudiosDeEstudiante.add(estudiosEntity.get());
        List<StudentEntity> estudianteDeEstudio = estudiosEntity.get().getStudentList();
        estudianteDeEstudio.add(student.get());

        student.get().setEstudios(estudiosDeEstudiante);
        estudiosEntity.get().setStudentList(estudianteDeEstudio);
      }
    }
    estudianteRepo.save(student.get());
    return new StudentOutputDTO(student.get());
  }

  @Override
  public ResponseEntity<?> deleteEstudios(String studentID, List<String> EstudioIDList)
      throws NotFoundException {
    Optional<StudentEntity> student = estudianteRepo.findById(studentID);
    //Comprobación lógica -(Existe el estudiante)
    if (student.isEmpty()) {
      throw new NotFoundException("No existe el estudiante");
    }
    for (String id : EstudioIDList) {
      Optional<EstudiosEntity> estudios = estudiosRepo.findById(id);
      //Comprobación lógica -(Existen los estudios asociados al estudiante)
      if (estudios.isPresent()) {
        estudios.get().getStudentList().remove(student.get());
        student.get().getEstudios().remove(estudios.get());
      }
    }
    estudianteRepo.save(student.get());
    return new ResponseEntity<>("Se han borrado las asignaturas", HttpStatus.OK);
  }
}
