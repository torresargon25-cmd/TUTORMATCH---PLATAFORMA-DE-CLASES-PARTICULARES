package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.EstudianteService;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.SesionTutoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/estudiante")
@RequiredArgsConstructor
public class ControllerEstudiante {

    private final EstudianteService estudianteService;
    private final SesionTutoriaService sesionTutoriaService;
    
    @GetMapping("listaEstudiantes")
    public String listEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.findAll());
        return "estudiante/listEstudiantes";
    }

    @GetMapping("/add")
    public String add(Model model) {

        Estudiante estudiante = new Estudiante();
        estudiante.setRol(Rol.ESTUDIANTE);

        model.addAttribute("estudiante", estudiante);

        return "estudiante/form_estudiante";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
        if (result.hasErrors()) {
            return "estudiante/form_estudiante";
        }
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
    public String editarFormEst(@PathVariable Long id, Model model) {
        Optional<Estudiante> editEstudiante = estudianteService.findById(id);
        if (editEstudiante.isPresent()) {
            model.addAttribute("estudiante", editEstudiante.get());
            return "estudiante/form_estudiante";
        }
        return "redirect:/estudiante/listaEstudiantes";
    }

    @PostMapping("/save/{id}")
    public String editar(@PathVariable Long id, @Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
        if (result.hasErrors()) {
            return "estudiante/form_estudiante";
        }
        estudiante.setId(id);
        estudianteService.edit(estudiante);
        return "redirect:/estudiante/listaEstudiantes";
    }

    @GetMapping("/detalle/{id}")
    public String detalleEstudiante(@PathVariable Long id, Model model) {
        Optional<Estudiante> estudiante = estudianteService.findById(id);
        if (estudiante.isPresent()) {
            model.addAttribute("estudiante", estudiante.get());
            return "estudiante/detaleEstudiante";
        }
        return "redirect:/estudiante/listaEstudiantes";
    }
    
    @GetMapping("/mi-perfil")
    public String miPerfil(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Estudiante estudiante = estudianteService.buscarPorUsername(username).orElseThrow();
        model.addAttribute("estudiante", estudiante);
        return "estudiante/miPerfil";
    }
    
    @GetMapping("/mis-estudiantes")
    public String misEstudiantes(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("estudiantes", sesionTutoriaService.findMisEstudiantes(username));
        return "estudiante/misEstudiantesTutor";
    }
}
	

