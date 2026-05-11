package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteService {

	private final EstudianteRepository estudianteRepository;
	
}
