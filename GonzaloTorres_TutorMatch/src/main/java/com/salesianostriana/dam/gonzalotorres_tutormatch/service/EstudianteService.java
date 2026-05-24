package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EstudianteService {

	
	
	//private List <Estudiante> listaEstudiantes = new ArrayList <Estudiante>(); ESTO NO
	private EstudianteRepository estudianterepository;
	
}
