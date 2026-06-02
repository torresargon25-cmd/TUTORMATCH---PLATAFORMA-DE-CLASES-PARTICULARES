package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.EstadoSesion;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelDificultadM;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelEstudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.DuracionInvalidaException;
import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.NivelEstudianteInvalidoException;
import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.SesionSolapadaException;
import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.TarifaInvalidaException;
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

    public void validarSolapamiento(Long tutorId, Long estudianteId,
                                     LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (!sesionTutoriaRepository.findSolapadasPorTutor(tutorId, fechaInicio, fechaFin).isEmpty()) {
            throw new SesionSolapadaException("El tutor ya tiene una sesión programada en ese horario");
        }
        if (!sesionTutoriaRepository.findSolapadasPorEstudiante(estudianteId, fechaInicio, fechaFin).isEmpty()) {
            throw new SesionSolapadaException("El estudiante ya tiene una sesión programada en ese horario");
        }
    }

    public void validarSolapamientoEdicion(Long tutorId, Long estudianteId, Long sesionId,
                                            LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (!sesionTutoriaRepository.findSolapadasPorTutorExcluyendo(tutorId, sesionId, fechaInicio, fechaFin).isEmpty()) {
            throw new SesionSolapadaException("El tutor ya tiene una sesión programada en ese horario");
        }
        if (!sesionTutoriaRepository.findSolapadasPorEstudianteExcluyendo(estudianteId, sesionId, fechaInicio, fechaFin).isEmpty()) {
            throw new SesionSolapadaException("El estudiante ya tiene una sesión programada en ese horario");
        }
    }

    public SesionTutoria calcularCoste(SesionTutoria sesion) {
        double coste = java.util.stream.Stream.of(sesion)
                .filter(s -> s.getTutor() != null && s.getTutor().getTarifaHora() != null)
                .mapToDouble(s -> {
                    long minutos = java.time.Duration.between(
                        s.getFechaInicio(), s.getFechaFin()).toMinutes();
                    double horas = minutos / 60.0;
                    return horas * s.getTutor().getTarifaHora();
                })
                .findFirst()
                .orElseThrow(() -> new TarifaInvalidaException(
                    "No se puede calcular el coste: tarifa no definida"));

        sesion.setCosteTotal(coste);
        return sesion;
    }
    
    public List<SesionTutoria> findMisSesiones(String username) {
        return sesionTutoriaRepository.findByEstudianteUsername(username);
    }
    
    public List<SesionTutoria> findMisSesionesPorEstado(String username, EstadoSesion estado) {
        return sesionTutoriaRepository.findByEstudianteUsernameAndEstado(username, estado);
    }
    
    public List<SesionTutoria> findSesionesProgramadasPorTutor(Long tutorId) {
        return sesionTutoriaRepository.findSesionesProgramadasPorTutor(tutorId);
    }
    
    public List<SesionTutoria> findSesionesEntreFechas(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
        return sesionTutoriaRepository.findSesionesentreDechas(fechaDesde, fechaHasta);
    }
    
    public List<SesionTutoria> findMisSesionesTutor(String username) {
        return sesionTutoriaRepository.findByTutorUsername(username);
    }

    public List<Estudiante> findMisEstudiantes(String username) {
        return sesionTutoriaRepository.findEstudiantesByTutorUsername(username);
    }
    
    public List<SesionTutoria> findByEstado(EstadoSesion estado) {
        return sesionTutoriaRepository.findByEstado(estado);
    }
    
    public List<SesionTutoria> findMisSesionesTutorPorEstado(String username, EstadoSesion estado) {
        return sesionTutoriaRepository.findByTutorUsernameAndEstado(username, estado);
    }
}
