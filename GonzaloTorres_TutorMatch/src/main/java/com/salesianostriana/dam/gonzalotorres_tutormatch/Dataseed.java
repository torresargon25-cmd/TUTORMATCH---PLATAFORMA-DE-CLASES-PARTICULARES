package com.salesianostriana.dam.gonzalotorres_tutormatch;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelEstudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.EstudianteRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Dataseed {

	private final EstudianteRepository estudianteRepository;
	
	@PostConstruct
	public void init() {
		
		estudianteRepository.save(
		Estudiante.builder()
		.nombre("Gonzalo")
		.apellidos("Torres Arroyo" )
		.email("a@gmail.com")
		.password("atencion")
		.rol(Rol.ESTUDIANTE)
		.nivel(NivelEstudiante.MEDIO)
		.telefono("623456789")
		.fechaNac(null)
		.fechaAlta(null)
		.nombreTutorLeg("Paco")
		.apellidosTutorLeg("Fernández Reino")
		.dniTutorLeg("34567984F")
		.build());
		
	}
	
	
}
