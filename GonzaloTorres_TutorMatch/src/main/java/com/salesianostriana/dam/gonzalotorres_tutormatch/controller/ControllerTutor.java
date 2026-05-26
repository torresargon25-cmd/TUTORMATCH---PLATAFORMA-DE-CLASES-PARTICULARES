package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Tutor;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.TutorService;

import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/tutor/")
@RequiredArgsConstructor
public class ControllerTutor {

	private final TutorService tutorService;
	
	@GetMapping("/new")
	public String addTutor (Model model) {
		model.addAttribute("tutor", new Tutor ());
		return "tutor/form_tutor";	
	}
	
	@PostMapping("save")
	public String saveTutor (@ModelAttribute Tutor tutor) {
		tutorService.save(tutor);
		return "redirect:/tutor/";	
	}
	
	@GetMapping("/")
	public String listTutors(Model model) {
	    model.addAttribute("tutores", tutorService.findAll());
	    return "tutor/listTutores";
	}
	
	@GetMapping("borrar/{id}")
	public String borrarTutor(@PathVariable Long id) {
	    Optional<Tutor> tutorABorrar = tutorService.findById(id);
	    if (tutorABorrar.isPresent()) {
	        tutorService.delete(tutorABorrar.get());
	    }
	    return "redirect:/tutor/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarTutor(@PathVariable Long id, Model model) {
	    Optional<Tutor> tutorAEditar = tutorService.findById(id);
	    if (tutorAEditar.isPresent()) {
	        model.addAttribute("tutor", tutorAEditar.get());
	        return "tutor/form_tutor";
	    }
	    return "redirect:/tutor/";
	}

	@PostMapping("/editar/{id}")
	public String guardarEdicion(@PathVariable Long id, @ModelAttribute Tutor tutor) {
	    tutor.setId(id);
	    tutorService.edit(tutor);
	    return "redirect:/tutor/";
	}
}
