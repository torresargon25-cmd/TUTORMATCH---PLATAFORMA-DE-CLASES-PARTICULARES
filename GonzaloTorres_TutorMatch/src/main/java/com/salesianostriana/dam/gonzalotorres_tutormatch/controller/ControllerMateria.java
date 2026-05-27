package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Materia;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.MateriaService;


import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/materia")
@RequiredArgsConstructor
public class ControllerMateria {

	private final MateriaService materiaService;
	
	 @GetMapping("/lista")
	    public String lista(Model model) {
	        model.addAttribute("materias", materiaService.findAll());
	        return "materia/listMaterias";
	    }

	    @GetMapping("/add")
	    public String add(Model model) {
	        model.addAttribute("materia", new Materia());
	        return "materia/form_materia";
	    }

	    @PostMapping("/save")
	    public String save(@ModelAttribute Materia materia) {
	        materiaService.save(materia);
	        return "redirect:/materia/lista";
	    } 
	
}
