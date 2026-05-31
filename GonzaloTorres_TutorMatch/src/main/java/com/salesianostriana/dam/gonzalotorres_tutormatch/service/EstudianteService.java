package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Tutor;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.EstudianteRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.TutorRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EstudianteService extends BaseServiceImpl<Estudiante, Long, EstudianteRepository>{

	
	private final EstudianteRepository estudianterepository;
	
	public Optional<Estudiante> buscarPorUsername(String username) {
	    return estudianterepository.findByUsername(username);
	}
	
}
