package com.salesianostriana.dam.gonzalotorres_tutormatch;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.EstadoSesion;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.EtapaEducativa;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelDificultadM;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelEstudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Materia;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.SesionTutoria;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Tutor;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.EstudianteRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.MateriaRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.SesionTutoriaRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.TutorRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Dataseed {

    private final EstudianteRepository estudianteRepository;
    private final TutorRepository tutorRepository;
    private final MateriaRepository materiaRepository;
    private final SesionTutoriaRepository sesiontutoriaRepository;
    private final PasswordEncoder passwordEncoder;

    private double calcularCoste(LocalDateTime inicio, LocalDateTime fin, double tarifaHora) {
        long minutos = Duration.between(inicio, fin).toMinutes();
        double horas = minutos / 60.0;
        return horas * tarifaHora;
    }

    @PostConstruct
    public void init() {

        // Estudiantes
        Estudiante e1 = Estudiante.builder()
                .nombre("Gonzalo")
                .apellidos("Torres Arroyo")
                .username("user")
                .email("user@user.com")
                .password(passwordEncoder.encode("user"))
                .nivel(NivelEstudiante.BAJO)
                .telefono("654789098")
                .esMenor(false)
                .rol(Rol.ESTUDIANTE)
                .nombreTutorLeg(null)
                .apellidosTutorLeg(null)
                .dniTutorLeg(null)
                .build();

        Estudiante e2 = Estudiante.builder()
                .nombre("Ana")
                .apellidos("Garcia Torres")
                .username("ana")
                .email("ana@tutormatch.com")
                .password(passwordEncoder.encode("ana123"))
                .nivel(NivelEstudiante.MEDIO)
                .telefono("600222333")
                .esMenor(true)
                .rol(Rol.ESTUDIANTE)
                .nombreTutorLeg("Jose")
                .apellidosTutorLeg("Garcia Martin")
                .dniTutorLeg("11223355X")
                .build();

        Estudiante e3 = Estudiante.builder()
                .nombre("Pablo")
                .apellidos("Torres Ruiz")
                .username("pablo")
                .email("pablo@tutormatch.com")
                .password(passwordEncoder.encode("pablo123"))
                .nivel(NivelEstudiante.ALTO)
                .telefono("600333444")
                .esMenor(false)
                .rol(Rol.ESTUDIANTE)
                .nombreTutorLeg(null)
                .apellidosTutorLeg(null)
                .dniTutorLeg(null)
                .build();

        estudianteRepository.saveAll(List.of(e1, e2, e3));

        // Tutores
        Tutor t1 = Tutor.builder()
                .nombre("Paco")
                .apellidos("Aguilar Ruíz")
                .username("admin")
                .email("admin@admin.com")
                .password(passwordEncoder.encode("admin"))
                .rol(Rol.ADMIN)
                .dni("46579809L")
                .especialidad("Matemáticas de Bachillerato")
                .tarifaHora(14.50)
                .puntuacionNivel(4.7)
                .imagen("https://media.v2.siweb.es/uploaded_thumb_medium/75ced0231b30d5bbba39592fef39e64d/fotografia_curriculum_foto_linkedin_corporativa_madrid_042.jpg")
                .build();

        Tutor t2 = Tutor.builder()
                .nombre("Carlos")
                .apellidos("Ruiz Lopez")
                .username("tutor")
                .email("carlos@tutormatch.com")
                .password(passwordEncoder.encode("tutor"))
                .rol(Rol.TUTOR)
                .dni("12345678A")
                .especialidad("Inglés ESO")
                .tarifaHora(12.00)
                .puntuacionNivel(4.5)
                .imagen("https://randomuser.me/api/portraits/men/32.jpg")
                .build();

        Tutor t3 = Tutor.builder()
                .nombre("Laura")
                .apellidos("Mendez Garcia")
                .username("laura")
                .email("laura@tutormatch.com")
                .password(passwordEncoder.encode("tutor2"))
                .rol(Rol.TUTOR)
                .dni("87654321B")
                .especialidad("Ingles")
                .tarifaHora(15.00)
                .puntuacionNivel(4.8)
                .imagen("https://randomuser.me/api/portraits/women/44.jpg")
                .build();

        tutorRepository.saveAll(List.of(t1, t2, t3));

        // Materias
        Materia m1 = Materia.builder()
                .nombre("Lengua")
                .dificultad(NivelDificultadM.BAJO)
                .descripcion("En esta asignatura se explicarán los conocimientos básicos requeridos para Lengua en etapa educativa de la ESO")
                .etapa(EtapaEducativa.BACHILLERATO)
                .imagen("https://ies-joseconde.centros.castillalamancha.es/sites/ies-joseconde.centros.castillalamancha.es/files/lengua.png")
                .build();

        Materia m2 = Materia.builder()
                .nombre("Matematicas ESO")
                .dificultad(NivelDificultadM.MEDIO)
                .descripcion("Algebra, geometria y estadistica para estudiantes de la ESO")
                .etapa(EtapaEducativa.ESO)
                .imagen("https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Simple_algebra_mnemonic.svg/1200px-Simple_algebra_mnemonic.svg.png")
                .build();

        Materia m3 = Materia.builder()
                .nombre("Matematicas Bachillerato")
                .dificultad(NivelDificultadM.ALTO)
                .descripcion("Calculo, algebra lineal y estadistica para estudiantes de Bachillerato")
                .etapa(EtapaEducativa.BACHILLERATO)
                .imagen("https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Simple_algebra_mnemonic.svg/1200px-Simple_algebra_mnemonic.svg.png")
                .build();

        Materia m4 = Materia.builder()
                .nombre("Inglés primaria")
                .dificultad(NivelDificultadM.MEDIO)
                .descripcion("Gramatica, vocabulario y conversacion en ingles para ESO y Bachillerato")
                .etapa(EtapaEducativa.PRIMARIA)
                .imagen("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1200px-Flag_of_the_United_Kingdom.svg.png")
                .build();

        Materia m5 = Materia.builder()
                .nombre("Fisica y Quimica Bachillerato")
                .dificultad(NivelDificultadM.ALTO)
                .descripcion("Mecanica, termodinamica y quimica organica para Bachillerato")
                .etapa(EtapaEducativa.BACHILLERATO)
                .imagen("https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Electron_shell_001_Hydrogen.svg/1200px-Electron_shell_001_Hydrogen.svg.png")
                .build();

        Materia m6 = Materia.builder()
                .nombre("Historia")
                .dificultad(NivelDificultadM.BAJO)
                .descripcion("Historia de España y Universal para estudiantes de ESO y Bachillerato")
                .etapa(EtapaEducativa.ESO)
                .imagen("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Parthenon_from_west.jpg/1200px-Parthenon_from_west.jpg")
                .build();

        materiaRepository.saveAll(List.of(m1, m2, m3, m4, m5, m6));

        // Sesiones con coste calculado
        LocalDateTime st1Inicio = LocalDateTime.of(2026, 6, 10, 10, 0);
        LocalDateTime st1Fin = LocalDateTime.of(2026, 6, 10, 11, 0);
        SesionTutoria st1 = SesionTutoria.builder()
                .fechaInicio(st1Inicio)
                .fechaFin(st1Fin)
                .costeTotal(calcularCoste(st1Inicio, st1Fin, t2.getTarifaHora()))
                .estado(EstadoSesion.PROGRAMADA)
                .observaciones("Repasar Matrices")
                .estudiante(e2)
                .tutor(t2)
                .materia(m3)
                .build();

        LocalDateTime st2Inicio = LocalDateTime.of(2026, 6, 11, 16, 0);
        LocalDateTime st2Fin = LocalDateTime.of(2026, 6, 11, 17, 30);
        SesionTutoria st2 = SesionTutoria.builder()
                .fechaInicio(st2Inicio)
                .fechaFin(st2Fin)
                .costeTotal(calcularCoste(st2Inicio, st2Fin, t3.getTarifaHora()))
                .estado(EstadoSesion.PROGRAMADA)
                .observaciones("Practica de Listening y Speaking")
                .estudiante(e1)
                .tutor(t3)
                .materia(m4)
                .build();

        LocalDateTime st3Inicio = LocalDateTime.of(2026, 5, 20, 9, 0);
        LocalDateTime st3Fin = LocalDateTime.of(2026, 5, 20, 10, 0);
        SesionTutoria st3 = SesionTutoria.builder()
                .fechaInicio(st3Inicio)
                .fechaFin(st3Fin)
                .costeTotal(calcularCoste(st3Inicio, st3Fin, t1.getTarifaHora()))
                .estado(EstadoSesion.FINALIZADA)
                .observaciones("Repaso de cinematica y dinamica")
                .estudiante(e3)
                .tutor(t1)
                .materia(m5)
                .build();

        LocalDateTime st4Inicio = LocalDateTime.of(2026, 5, 15, 11, 0);
        LocalDateTime st4Fin = LocalDateTime.of(2026, 5, 15, 13, 0);
        SesionTutoria st4 = SesionTutoria.builder()
                .fechaInicio(st4Inicio)
                .fechaFin(st4Fin)
                .costeTotal(calcularCoste(st4Inicio, st4Fin, t3.getTarifaHora()))
                .estado(EstadoSesion.CANCELADA)
                .observaciones("Cancelada por el estudiante")
                .estudiante(e2)
                .tutor(t3)
                .materia(m1)
                .build();

        LocalDateTime st5Inicio = LocalDateTime.of(2026, 6, 15, 17, 0);
        LocalDateTime st5Fin = LocalDateTime.of(2026, 6, 15, 19, 0);
        SesionTutoria st5 = SesionTutoria.builder()
                .fechaInicio(st5Inicio)
                .fechaFin(st5Fin)
                .costeTotal(calcularCoste(st5Inicio, st5Fin, t2.getTarifaHora()))
                .estado(EstadoSesion.PROGRAMADA)
                .observaciones("Preparacion examen de selectividad")
                .estudiante(e3)
                .tutor(t2)
                .materia(m3)
                .build();

        LocalDateTime st6Inicio = LocalDateTime.of(2026, 5, 28, 10, 0);
        LocalDateTime st6Fin = LocalDateTime.of(2026, 5, 28, 11, 0);
        SesionTutoria st6 = SesionTutoria.builder()
                .fechaInicio(st6Inicio)
                .fechaFin(st6Fin)
                .costeTotal(calcularCoste(st6Inicio, st6Fin, t2.getTarifaHora()))
                .estado(EstadoSesion.FINALIZADA)
                .observaciones("Repaso de oraciones subordinadas")
                .estudiante(e1)
                .tutor(t2)
                .materia(m1)
                .build();

        sesiontutoriaRepository.save(st1);
        sesiontutoriaRepository.save(st2);
        sesiontutoriaRepository.save(st3);
        sesiontutoriaRepository.save(st4);
        sesiontutoriaRepository.save(st5);
        sesiontutoriaRepository.save(st6);
    }
}
