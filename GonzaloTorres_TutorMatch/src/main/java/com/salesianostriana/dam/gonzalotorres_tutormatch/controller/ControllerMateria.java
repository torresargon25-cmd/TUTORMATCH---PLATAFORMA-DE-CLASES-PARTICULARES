package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Materia;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.MateriaService;
import jakarta.validation.Valid;
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
    public String save(@Valid @ModelAttribute Materia materia, BindingResult result) {
        if (result.hasErrors()) {
            return "materia/form_materia";
        }
        materiaService.save(materia);
        return "redirect:/materia/lista";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id) {
        Optional<Materia> materia = materiaService.findById(id);
        if (materia.isPresent()) {
            materiaService.delete(materia.get());
        }
        return "redirect:/materia/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Materia> materia = materiaService.findById(id);
        if (materia.isPresent()) {
            model.addAttribute("materia", materia.get());
            return "materia/form_materia";
        }
        return "redirect:/materia/lista";
    }

    @PostMapping("/guardar/{id}")
    public String editar(@PathVariable Long id, @Valid @ModelAttribute Materia materia, BindingResult result) {
        if (result.hasErrors()) {
            return "materia/form_materia";
        }
        materia.setId(id);
        materiaService.edit(materia);
        return "redirect:/materia/lista";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Optional<Materia> materia = materiaService.findById(id);
        if (materia.isPresent()) {
            model.addAttribute("materia", materia.get());
            return "materia/detaleMateria";
        }
        return "redirect:/materia/lista";
    }
}
