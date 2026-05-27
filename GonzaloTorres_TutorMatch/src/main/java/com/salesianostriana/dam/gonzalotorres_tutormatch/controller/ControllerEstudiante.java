package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;	

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/delete/{id}")
	public String borrar(@PathVariable Long id) {

	    Optional<Estudiante> estudiante = estudianteService.findById(id);

	    if (estudiante.isPresent()) {
	        estudianteService.delete(estudiante.get());
	    }
	    return "redirect:/estudiante/listaEstudiantes";
	}
	@GetMapping("/editar/{id}")
	public String editarFormEst (@PathVariable Long id, Model model) {
		Optional<Estudiante> editEstudiante = estudianteService.findById(id);
		
		if(editEstudiante.isPresent()) {
			model.addAttribute("estudiante", editEstudiante.get());
			return "estudiante/form_estudiante";
		}else {
			return "redirect:estudiante/listEstudiantes";
		}
		
	}
	@PostMapping("/save/{id}")
	public String editar(@PathVariable Long id, @ModelAttribute Estudiante estudiante) {
	    estudiante.setId(id);
	    estudianteService.edit(estudiante);
	    return "redirect:/estudiante/listaEstudiantes";
	}	
	}

	

