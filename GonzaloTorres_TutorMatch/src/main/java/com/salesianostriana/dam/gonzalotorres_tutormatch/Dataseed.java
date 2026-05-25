package com.salesianostriana.dam.gonzalotorres_tutormatch;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelEstudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante.EstudianteBuilder;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.SesionTutoria;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.EstudianteRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.MateriaRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.TutorRepository;

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
		
		Estudiante e1 = Estudiante.//Build????
				.nivel(NivelEstudiante.ALTO)
				.telefono("654789098")
				.esMenor(false)
				.nombreTutorLeg(null)
				.apellidosTutorLeg(null)
				.dniTutorLeg(null)
				.build();
				
		
	}
	
	
}
