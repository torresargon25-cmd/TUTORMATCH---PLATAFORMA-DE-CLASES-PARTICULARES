package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;
import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.AdminNoBorrableException;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.SesionTutoria;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Tutor;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.MateriaService;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.SesionTutoriaService;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.TutorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/tutor/")
@RequiredArgsConstructor
public class ControllerTutor {

    private final TutorService tutorService;
    private final SesionTutoriaService sesionService;
    private final MateriaService materiaService;

    @GetMapping("/new")
    public String addTutor(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutor/form_tutor";
    }

    @PostMapping("save")
    public String saveTutor(@Valid @ModelAttribute Tutor tutor, BindingResult result) {
        if (result.hasErrors()) {
            return "tutor/form_tutor";
        }
        tutorService.validarTarifa(tutor.getTarifaHora());
        tutorService.save(tutor);
        return "redirect:/tutor/";
    }

    @GetMapping("/")
    public String listTutors(Model model) {
        model.addAttribute("tutores", tutorService.findAll());
        return "tutor/listTutores";
    }

    @GetMapping("borrar/{id}")
    public String borrarTutor(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            Optional<Tutor> tutorABorrar = tutorService.findById(id);
            if (tutorABorrar.isPresent()) {
                if (tutorABorrar.get().getRol() == Rol.ADMIN) {
                    throw new AdminNoBorrableException();
                }
                tutorService.delete(tutorABorrar.get());
            }
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorBorrar",
                "No se puede eliminar el tutor porque tiene sesiones asociadas.");
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
    public String guardarEdicion(@PathVariable Long id, @Valid @ModelAttribute Tutor tutor, BindingResult result) {
        if (result.hasErrors()) {
            return "tutor/form_tutor";
        }
        tutorService.validarTarifa(tutor.getTarifaHora());
        tutor.setId(id);
        tutorService.edit(tutor);
        return "redirect:/tutor/";
    }

    @GetMapping("detalle/{id}")
    public String detalleTutor(@PathVariable Long id, Model model) {
        Optional<Tutor> tutor = tutorService.findById(id);
        if (tutor.isPresent()) {
            model.addAttribute("tutor", tutor.get());
            return "tutor/detaleTutor";
        }
        return "redirect:/tutor/";
    }

    @GetMapping("/reservar/{id}")
    public String verTutorParaReservar(@PathVariable Long id, Model model) {
        Tutor tutor = tutorService.findById(id).orElseThrow();
        model.addAttribute("tutor", tutor);
        model.addAttribute("materias", materiaService.findAll());
        model.addAttribute("sesionesOcupadas", sesionService.findSesionesProgramadasPorTutor(id));
        return "tutor/detaleTutorEstudiante";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String especialidad, Model model) {
        List<Tutor> tutores = (especialidad != null && !especialidad.isBlank())
                ? tutorService.buscarPorEspecialidad(especialidad)
                : tutorService.findAll();

        Map<Long, List<SesionTutoria>> sesionesPorTutor = tutores.stream()
                .collect(Collectors.toMap(
                        Tutor::getId,
                        t -> sesionService.findSesionesProgramadasPorTutor(t.getId())
                ));

        model.addAttribute("tutores", tutores);
        model.addAttribute("sesionesPorTutor", sesionesPorTutor);
        model.addAttribute("especialidad", especialidad);
        return "tutor/buscarTutor";
    }
}
