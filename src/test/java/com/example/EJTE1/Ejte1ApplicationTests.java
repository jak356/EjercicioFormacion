package com.example.EJTE1;

import com.example.EJTE1.ErrorHandlers.NotFoundException;
import com.example.EJTE1.ErrorHandlers.UnprocesableException;
import com.example.EJTE1.Persona.PersonaService;
import com.example.EJTE1.domain.PersonaEntity;
import com.example.EJTE1.dto.PersonaInputDTO;
import com.example.EJTE1.dto.PersonaOutPutDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class Ejte1ApplicationTests {
    @Mock
    PersonaService personaServiceMock;

    @InjectMocks
    ControladorPersona personaControlador;


    PersonaInputDTO personaInputDTO =
            new PersonaInputDTO(
                    1,
                    "javier01",
                    "",
                    "Javier",
                    "Jimenez",
                    "Jak234@gmail.com",
                    "123@gmail.com",
                    "Sevilla",
                    true,
                    new Date(),
                    "",
                    new Date());
    PersonaOutPutDTO personaOutPutDTO =
            new PersonaOutPutDTO(
                    1,
                    "javier01",
                    "",
                    "Javier",
                    "Jimenez",
                    "Jak234@gmail.com",
                    "123@gmail.com",
                    "Sevilla",
                    true,
                    new Date(),
                    "",
                    new Date());
    List<PersonaOutPutDTO> personaList;

    @org.junit.jupiter.api.BeforeEach
    public void beforeTest() {
        System.out.println("Before");
        personaList = new ArrayList<>();
        personaList.add(personaOutPutDTO);
    }

    @Test
    void findByIdTest() throws NotFoundException {
        System.out.println("test findbyid");
        when(personaServiceMock.findById(1)).thenReturn(personaOutPutDTO);
        assertEquals(personaOutPutDTO, personaControlador.obtenerPorID(1));
        System.out.println(personaOutPutDTO);
    }

    @Test
    void findAllTest() {
        System.out.println("test findall");
        when(personaServiceMock.findAll()).thenReturn(personaList);
        assertEquals(personaList, personaControlador.obtenerTodos());
        System.out.println(personaList);
    }

    @Test
    void findByNameTest() throws NotFoundException {
        System.out.println("test findbyname");
        when(personaServiceMock.findByName("Javier")).thenReturn(personaList);
        assertEquals(personaList, personaControlador.obtenerPorName("Javier"));
        System.out.println(personaList);
    }

    @Test
    void crearPersonaTest() throws UnprocesableException {
        System.out.println("test crearpersona");
        when(personaServiceMock.addPersona(personaInputDTO)).thenReturn(new PersonaOutPutDTO(new PersonaEntity(personaInputDTO)));
        assertEquals(personaOutPutDTO, personaControlador.crearPersona(personaInputDTO));
        System.out.println(personaOutPutDTO);
    }

    @Test
    void deletePersonaTest() throws NotFoundException {
        System.out.println("test deletepersona");
        when(personaServiceMock.deleteById(1)).thenReturn(new PersonaEntity(personaInputDTO));
        assertEquals(new PersonaEntity(personaInputDTO), personaControlador.eleminarPorID(1));

    }

}
