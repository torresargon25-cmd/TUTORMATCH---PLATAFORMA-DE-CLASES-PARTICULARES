package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import org.springframework.stereotype.Controller;	

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.EstudianteService;


import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/estudiante")
@RequiredArgsConstructor
public class ControllerEstudiante {

	private final EstudianteService estudianteService;
	
	@GetMapping("listaEstudiantes")
	public String listEstudiantes(Model model) {
	    model.addAttribute("estudiantes", estudianteService.findAll());
	    return "estudiante/listEstudiantes";
	}
	@GetMapping("/add")
	public String add (Model model) {
		model.addAttribute("estudiante", new Estudiante());
		return "estudiante/form_estudiante";	
	}
	@PostMapping("/save")
	public String save (@ModelAttribute Estudiante estudiante) {
		estudianteService.save(estudiante);
		return "redirect:/estudiante/listaEstudiantes";
	}
}
	

