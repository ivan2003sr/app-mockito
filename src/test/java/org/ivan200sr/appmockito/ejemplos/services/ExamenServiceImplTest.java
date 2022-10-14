package org.ivan200sr.appmockito.ejemplos.services;

import org.ivan200sr.appmockito.ejemplos.models.Examen;
import org.ivan200sr.appmockito.ejemplos.repositories.ExamenRepository;
import org.ivan200sr.appmockito.ejemplos.repositories.PreguntaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {
    @Mock
    ExamenRepository repository;
    @Mock
    PreguntaRepository preguntaRepository;

    @InjectMocks
    ExamenServiceImpl service;

//    ExamenService service;

    /*si extendemos con mockito class, no hace falta
    @BeforeEach
    void setUp() {
   //     repository = mock(ExamenRepositoryOtro.class);
   //     preguntaRepository = mock(PreguntaRepository.class);
   //     service = new ExamenServiceImpl(repository,preguntaRepository);
        MockitoAnnotations.openMocks(this);
    }*/

    @Test
    void findExamenPorNombre() {
        when(repository.findAll()).thenReturn(Data.EXAMENES);
        Optional <Examen> examen = service.findExamenPorNombre("Matematicas");

        assertTrue(examen.isPresent());
        assertEquals(5L,examen.orElseThrow().getId());
        assertEquals("Matematicas", examen.orElseThrow().getNombre());
    }

    @Test
    void findExamenPorNombreEmpty() {
        List<Examen> datos = Collections.emptyList();
        when(repository.findAll()).thenReturn(datos);
        Optional <Examen> examen = service.findExamenPorNombre("Matematicas");

        assertFalse(examen.isPresent());
    }

    @Test
    void testPreguntasExamen() {
        when(repository.findAll()).thenReturn(Data.EXAMENES);
        when(preguntaRepository.findPreguntarPorExamenId(anyLong())).thenReturn(Data.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas");
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Aritmética"));
    }

    @Test
    void testPreguntasExamenVerify() {
        when(repository.findAll()).thenReturn(Data.EXAMENES);
        when(preguntaRepository.findPreguntarPorExamenId(anyLong())).thenReturn(Data.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas");
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Aritmética"));
        verify(repository).findAll();
        verify(preguntaRepository).findPreguntarPorExamenId(5L);
    }

    @Test
    void testNoExisteExamenVerify() {
        //Given
        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(preguntaRepository.findPreguntarPorExamenId(anyLong())).thenReturn(Data.PREGUNTAS);

        //when
        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas");

        //then
        assertNull(examen);
        verify(repository).findAll();
        verify(preguntaRepository).findPreguntarPorExamenId(5L);
    }

    @Test
    void testGuardarExamen(){
        // Given
        Examen newExamen = Data.EXAMEN;
        newExamen.setPreguntas(Data.PREGUNTAS);

        when(repository.guardar(any(Examen.class))).then(new Answer<Examen>(){

            Long secuencia = 8L;

            @Override
            public Examen answer(InvocationOnMock invocation) throws Throwable {
                Examen examen = invocation.getArgument(0);
                examen.setId(++secuencia);
                return examen;
            }
        });
        // When
        Examen examen = service.guardar(newExamen);
        assertNotNull(examen.getId());
        assertEquals(9L, examen.getId());
        assertEquals("Física",examen.getNombre());

        verify(repository).guardar(any(Examen.class));
        verify(preguntaRepository).guardarVarias(anyList());

    }
}