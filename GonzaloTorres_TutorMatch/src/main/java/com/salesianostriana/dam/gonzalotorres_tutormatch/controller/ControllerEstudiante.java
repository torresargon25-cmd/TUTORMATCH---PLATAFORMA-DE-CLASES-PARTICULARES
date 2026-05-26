package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.gonzalotorres_tutormatch.service.EstudianteService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@Controller
@RequestMapping("/estudiante/")
@AllArgsConstructor
public class ControllerEstudiante {

	private final EstudianteService estudianteService;
	
}
