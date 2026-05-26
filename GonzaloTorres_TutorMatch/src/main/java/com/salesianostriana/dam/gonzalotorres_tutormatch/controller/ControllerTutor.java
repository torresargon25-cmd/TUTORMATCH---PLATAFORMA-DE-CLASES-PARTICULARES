package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		return "form_tutor";
		
	}
	@PostMapping("save")
	public String saveTutor (@ModelAttribute Tutor tutor) {
		tutorService.save(tutor);
		return "redirect:/tutor/";
		
		
	}
	
}
