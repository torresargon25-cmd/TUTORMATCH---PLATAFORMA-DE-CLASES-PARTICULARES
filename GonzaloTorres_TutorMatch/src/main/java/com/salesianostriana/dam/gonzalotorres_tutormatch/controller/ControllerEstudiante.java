package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.gonzalotorres_tutormatch.service.EstudianteService;


import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/producto/")
@RequiredArgsConstructor
public class ControllerEstudiante {

	private final EstudianteService estudianteService;
	
	@GetMapping("/ListVacia")
	public String listaVac(Model model) {
		model.addAttribute("estudiantes", estudianteService.getLista());
		
		System.out.println(estudianteService.getLista());
		return "listaVacia";
	}
	
}
