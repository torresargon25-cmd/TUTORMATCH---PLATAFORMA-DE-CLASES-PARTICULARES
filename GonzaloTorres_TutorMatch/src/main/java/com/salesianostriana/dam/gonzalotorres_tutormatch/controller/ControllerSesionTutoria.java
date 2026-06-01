package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
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
            @RequestParam Long materiaId,
            Model model) {

        if (fechaFin.isBefore(fechaInicio) || fechaFin.isEqual(fechaInicio)) {
            model.addAttribute("tutores", tutorService.findAll());
            model.addAttribute("estudiantes", estudianteService.findAll());
            model.addAttribute("materias", materiaService.findAll());
            model.addAttribute("estados", EstadoSesion.values());
            model.addAttribute("errorFecha", "La fecha de fin debe ser posterior a la fecha de inicio");
            return "sesion/form_sesion";
        }

        Tutor tutor = tutorService.findById(tutorId).orElseThrow();
        Estudiante estudiante = estudianteService.findById(estudianteId).orElseThrow();
        Materia materia = materiaService.findById(materiaId).orElseThrow();

        sesionService.validarDuracion(fechaInicio, fechaFin);
        sesionService.validarNivelEstudiante(estudiante, materia);
        sesionService.validarSolapamiento(tutorId, estudianteId, fechaInicio, fechaFin);

        SesionTutoria sesion = SesionTutoria.builder()
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .estado(estado)
                .observaciones(observaciones)
                .tutor(tutor)
                .estudiante(estudiante)
                .materia(materia)
                .build();

        sesionService.calcularCoste(sesion);
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

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<SesionTutoria> sesion = sesionService.findById(id);
        if (sesion.isPresent()) {
            model.addAttribute("sesion", sesion.get());
            model.addAttribute("tutores", tutorService.findAll());
            model.addAttribute("estudiantes", estudianteService.findAll());
            model.addAttribute("materias", materiaService.findAll());
            model.addAttribute("estados", EstadoSesion.values());
            return "sesion/form_sesion_editar";
        }
        return "redirect:/sesion/lista";
    }

    @PostMapping("/save/{id}")
    public String editar(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            @RequestParam EstadoSesion estado,
            @RequestParam(required = false) String observaciones,
            @RequestParam Long tutorId,
            @RequestParam Long estudianteId,
            @RequestParam Long materiaId,
            Model model) {

        if (fechaFin.isBefore(fechaInicio) || fechaFin.isEqual(fechaInicio)) {
            model.addAttribute("sesion", sesionService.findById(id).orElseThrow());
            model.addAttribute("tutores", tutorService.findAll());
            model.addAttribute("estudiantes", estudianteService.findAll());
            model.addAttribute("materias", materiaService.findAll());
            model.addAttribute("estados", EstadoSesion.values());
            model.addAttribute("errorFecha", "La fecha de fin debe ser posterior a la fecha de inicio");
            return "sesion/form_sesion_editar";
        }

        Tutor tutor = tutorService.findById(tutorId).orElseThrow();
        Estudiante estudiante = estudianteService.findById(estudianteId).orElseThrow();
        Materia materia = materiaService.findById(materiaId).orElseThrow();

        sesionService.validarDuracion(fechaInicio, fechaFin);
        sesionService.validarNivelEstudiante(estudiante, materia);
        sesionService.validarSolapamientoEdicion(tutorId, estudianteId, id, fechaInicio, fechaFin);

        SesionTutoria sesion = SesionTutoria.builder()
                .id(id)
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .estado(estado)
                .observaciones(observaciones)
                .tutor(tutor)
                .estudiante(estudiante)
                .materia(materia)
                .build();

        sesionService.calcularCoste(sesion);
        sesionService.edit(sesion);
        return "redirect:/sesion/lista";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Optional<SesionTutoria> sesion = sesionService.findById(id);
        if (sesion.isPresent()) {
            model.addAttribute("sesion", sesion.get());
            return "sesion/detaleSesion";
        }
        return "redirect:/sesion/lista";
    }

    @GetMapping("/mis-sesiones")
    public String misSesiones(@RequestParam(required = false) EstadoSesion estado, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<SesionTutoria> sesiones;
        if (estado != null) {
            sesiones = sesionService.findMisSesionesPorEstado(username, estado);
        } else {
            sesiones = sesionService.findMisSesiones(username);
        }
        model.addAttribute("sesiones", sesiones);
        return "sesion/mis_sesiones";
    }

    @PostMapping("/reservar")
    public String reservar(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            @RequestParam Long tutorId,
            @RequestParam Long materiaId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Estudiante estudiante = estudianteService.buscarPorUsername(username).orElseThrow();
        Tutor tutor = tutorService.findById(tutorId).orElseThrow();
        Materia materia = materiaService.findById(materiaId).orElseThrow();

        sesionService.validarDuracion(fechaInicio, fechaFin);
        sesionService.validarNivelEstudiante(estudiante, materia);
        sesionService.validarSolapamiento(tutorId, estudiante.getId(), fechaInicio, fechaFin);

        SesionTutoria sesion = SesionTutoria.builder()
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .estado(EstadoSesion.PROGRAMADA)
                .tutor(tutor)
                .estudiante(estudiante)
                .materia(materia)
                .build();

        sesionService.calcularCoste(sesion);
        sesionService.save(sesion);
        return "redirect:/sesion/mis-sesiones";
    }
    
    @GetMapping("/entre-fechas")
    public String entreFechas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta,
            Model model) {

        if (fechaDesde != null && fechaHasta != null) {
            model.addAttribute("sesiones", sesionService.findSesionesEntreFechas(fechaDesde, fechaHasta));
            model.addAttribute("fechaDesde", fechaDesde);
            model.addAttribute("fechaHasta", fechaHasta);
        }
        return "sesion/entreFechas";
    }
    
    @GetMapping("/mis-sesiones-tutor")
    public String misSesionesTutor(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("sesiones", sesionService.findMisSesionesTutor(username));
        return "sesion/misSesionesTutor";
    }
}

