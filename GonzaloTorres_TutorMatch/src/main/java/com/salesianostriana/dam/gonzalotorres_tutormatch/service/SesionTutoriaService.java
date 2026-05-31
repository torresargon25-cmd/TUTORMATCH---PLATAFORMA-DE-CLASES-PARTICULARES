package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelDificultadM;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelEstudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.DuracionInvalidaException;
import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.NivelEstudianteInvalidoException;
import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.SesionSolapadaException;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Materia;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.SesionTutoria;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.SesionTutoriaRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SesionTutoriaService extends BaseServiceImpl<SesionTutoria, Long, SesionTutoriaRepository> {

    private final SesionTutoriaRepository sesionTutoriaRepository;

    public void validarDuracion(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        long minutos = java.time.Duration.between(fechaInicio, fechaFin).toMinutes();
        if (minutos < 30) {
            throw new DuracionInvalidaException("La sesión debe durar al menos 30 minutos");
        }
        if (minutos > 480) {
            throw new DuracionInvalidaException("La sesión no puede durar más de 8 horas");
        }
    }
    public void validarNivelEstudiante(Estudiante estudiante, Materia materia) {
        if (estudiante.getNivel() == NivelEstudiante.BAJO 
                && materia.getDificultad() == NivelDificultadM.ALTO) {
            throw new NivelEstudianteInvalidoException(
                "El estudiante de nivel BAJO no puede acceder a materias de dificultad ALTA");
        }
    }
    
}
