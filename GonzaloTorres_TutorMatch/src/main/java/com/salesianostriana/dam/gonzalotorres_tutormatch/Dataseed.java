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
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.TutorRepository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Dataseed {

	private final EstudianteRepository estudianteRepository;
	private final TutorRepository tutorRepository;
	private final MateriaRepository materiaRepository;
	private final SesionTutoria sesiontutoriaRepository;
	
	
	
	@PostConstruct
	public void init() {
		
		//Estudiantes
		
		Estudiante e1 = Estudiante.builder()
				.nombre("Gonzalo")
				.apellidos("Torres Arroyo")
				.email("user@user.com")
				.password("user")
				.nivel(NivelEstudiante.BAJO)
				.telefono("654789098")
				.esMenor(false)
				.nombreTutorLeg(null)
				.apellidosTutorLeg(null)
				.dniTutorLeg(null)
				.build();
		Estudiante e2 = Estudiante.builder()
			    .nombre("Ana")
			    .apellidos("Garcia Torres")
			    .email("ana@tutormatch.com")
			    .password("ana123")
			    .nivel(NivelEstudiante.MEDIO)
			    .telefono("600222333")
			    .esMenor(true)
			    .nombreTutorLeg("Jose")
			    .apellidosTutorLeg("Garcia Martin")
			    .dniTutorLeg("11223355X")
			    .build();

			Estudiante e3 = Estudiante.builder()
			    .nombre("Pablo")
			    .apellidos("Torres Ruiz")
			    .email("pablo@tutormatch.com")
			    .password("pablo123")
			    .nivel(NivelEstudiante.ALTO)
			    .telefono("600333444")
			    .esMenor(false)
			    .nombreTutorLeg(null)
			    .apellidosTutorLeg(null)
			    .dniTutorLeg(null)
			    .build();
			Tutor t1 = Tutor.builder()
				.nombre("Paco")
				.apellidos("Aguilar Ruíz")
				.email("admin@admin.com")
				.password("admin")
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
				    .email("carlos@tutormatch.com")
				    .password("tutor1")
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
				    .email("laura@tutormatch.com")
				    .password("tutor2")
				    .rol(Rol.TUTOR)
				    .dni("87654321B")
				    .especialidad("Ingles")
				    .tarifaHora(15.00)
				    .puntuacionNivel(4.8)
				    .imagen("https://randomuser.me/api/portraits/women/44.jpg")
				    .build();
				
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
				
				SesionTutoria st1 = SesionTutoria.builder()
						.fechaInicio(LocalDateTime.of(2000, 1, 1, 0, 0))
						.fechaFin(LocalDateTime.of(2026, 12, 31, 23, 59))
						.costeTotal(null)
						.estado(EstadoSesion.PROGRAMADA)
						.observaciones("Repasar Matrices")
						.estudiante(e2)
						.tutor(t2)
						.materia(m3)
						.build();
				
				SesionTutoria st2 = SesionTutoria.builder()
					    .fechaInicio(LocalDateTime.of(2026, 6, 11, 16, 0))
					    .fechaFin(LocalDateTime.of(2026, 6, 11, 17, 30))
					    .costeTotal(null)
					    .estado(EstadoSesion.PROGRAMADA)
					    .observaciones("Practica de Listening y Speaking")
					    .estudiante(e1)
					    .tutor(t3)
					    .materia(m4)
					    .build();

					SesionTutoria st3 = SesionTutoria.builder()
					    .fechaInicio(LocalDateTime.of(2026, 5, 20, 9, 0))
					    .fechaFin(LocalDateTime.of(2026, 5, 20, 10, 0))
					    .costeTotal(null)
					    .estado(EstadoSesion.FINALIZADA)
					    .observaciones("Repaso de cinematica y dinamica")
					    .estudiante(e3)
					    .tutor(t1)
					    .materia(m5)
					    .build();

					SesionTutoria st4 = SesionTutoria.builder()
					    .fechaInicio(LocalDateTime.of(2026, 5, 15, 11, 0))
					    .fechaFin(LocalDateTime.of(2026, 5, 15, 13, 0))
					    .costeTotal(null)
					    .estado(EstadoSesion.CANCELADA)
					    .observaciones("Cancelada por el estudiante")
					    .estudiante(e2)
					    .tutor(t3)
					    .materia(m1)
					    .build();

					SesionTutoria st5 = SesionTutoria.builder()
					    .fechaInicio(LocalDateTime.of(2026, 6, 15, 17, 0))
					    .fechaFin(LocalDateTime.of(2026, 6, 15, 19, 0))
					    .costeTotal(null)
					    .estado(EstadoSesion.PROGRAMADA)
					    .observaciones("Preparacion examen de selectividad")
					    .estudiante(e3)
					    .tutor(t2)
					    .materia(m3)
					    .build();

					SesionTutoria st6 = SesionTutoria.builder()
					    .fechaInicio(LocalDateTime.of(2026, 5, 28, 10, 0))
					    .fechaFin(LocalDateTime.of(2026, 5, 28, 11, 0))
					    .costeTotal(null)
					    .estado(EstadoSesion.FINALIZADA)
					    .observaciones("Repaso de oraciones subordinadas")
					    .estudiante(e1)
					    .tutor(t2)
					    .materia(m1)
					    .build();
					
	}
	
	
}
