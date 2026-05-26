package com.salesianostriana.dam.gonzalotorres_tutormatch.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final TutorService tutorService;
    private final EstudianteService estudianteService;
    private final MateriaService materiaService;
    private final SesionTutoriaService sesionTutoriaService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin/")
    public String adminPanel(Model model) {
        model.addAttribute("totalTutores", tutorService.count());
        model.addAttribute("totalEstudiantes", estudianteService.count());
        model.addAttribute("totalMaterias", materiaService.count());
        model.addAttribute("totalSesiones", sesionTutoriaService.count());
        return "admin/index";
    }
}
