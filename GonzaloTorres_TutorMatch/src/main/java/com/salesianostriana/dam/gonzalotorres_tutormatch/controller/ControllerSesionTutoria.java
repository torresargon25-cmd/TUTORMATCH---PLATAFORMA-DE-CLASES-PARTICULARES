package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.EstadoSesion;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Materia;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.SesionTutoria;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Tutor;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.EstudianteService;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.MateriaService;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.SesionTutoriaService;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.TutorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/sesion")
@RequiredArgsConstructor
public class ControllerSesionTutoria {

    private final SesionTutoriaService sesionService;
    private final TutorService tutorService;
    private final EstudianteService estudianteService;
    private final MateriaService materiaService;

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("sesiones", sesionService.findAll());
        return "sesion/listSesiones";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("tutores", tutorService.findAll());
        model.addAttribute("estudiantes", estudianteService.findAll());
        model.addAttribute("materias", materiaService.findAll());
        model.addAttribute("estados", EstadoSesion.values());
        return "sesion/form_sesion";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            @RequestParam EstadoSesion estado,
            @RequestParam(required = false) String observaciones,
            @RequestParam Long tutorId,
            @RequestParam Long estudianteId,
            @RequestParam Long materiaId) {

        Tutor tutor = tutorService.findById(tutorId).orElseThrow();
        Estudiante estudiante = estudianteService.findById(estudianteId).orElseThrow();
        Materia materia = materiaService.findById(materiaId).orElseThrow();

        SesionTutoria sesion = SesionTutoria.builder()
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .estado(estado)
                .observaciones(observaciones)
                .tutor(tutor)
                .estudiante(estudiante)
                .materia(materia)
                .build();

        sesionService.save(sesion);
        return "redirect:/sesion/lista";
    }
    
    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id) {
        Optional<SesionTutoria> sesion = sesionService.findById(id);
        if (sesion.isPresent()) {
            sesionService.delete(sesion.get());
        }
        return "redirect:/sesion/lista";
    }
}
