package com.cibertec.controller;

import com.cibertec.model.Curso; 
import com.cibertec.model.Estudiante;
import com.cibertec.service.CursoService;
import com.cibertec.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private CursoService cursoService; 

    // metodo lista
    @GetMapping
    public String verListaEstudiantes(Model model) {
        List<Estudiante> estudiantes = estudianteService.findAll();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantelista"; // Devuelve la vista estudiantelista.html
    }

    // metodo agregar
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("estudiante", new Estudiante()); 
        model.addAttribute("cursos", cursoService.findAll()); 
        return "estudianteform"; 
    }

    @PostMapping("/agregar")
    public String agregarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.save(estudiante);
        return "redirect:/estudiantes"; 
    }

    // metodo editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Estudiante> estudianteOpt = estudianteService.findById(id);
        if (estudianteOpt.isPresent()) {
            model.addAttribute("estudiante", estudianteOpt.get());
            model.addAttribute("cursos", cursoService.findAll()); 
            return "editarestudiante"; 
        }
        return "redirect:/estudiantes"; 
    }

    @PostMapping("/editar")
    public String editarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.save(estudiante);
        return "redirect:/estudiantes"; 
    }

    // metodo eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.deleteById(id);
        return "redirect:/estudiantes"; 
    }
}

