package com.salesianostriana.dam.gonzalotorres_tutormatch;

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
		.apellidos(null)
		.
		.build());
		
	}
	
	
}
