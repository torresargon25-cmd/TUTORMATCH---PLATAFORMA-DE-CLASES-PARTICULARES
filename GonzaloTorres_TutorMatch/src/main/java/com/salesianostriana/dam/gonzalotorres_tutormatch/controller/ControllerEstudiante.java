package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.EstudianteRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.EstudianteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/producto/")
@RequiredArgsConstructor
public class ControllerEstudiante {

	private final EstudianteService estudianteService;
	
}
