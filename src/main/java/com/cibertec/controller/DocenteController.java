package com.cibertec.controller;

import com.cibertec.model.Docente;
import com.cibertec.model.Profesion;
import com.cibertec.service.DocenteService;
import com.cibertec.service.ProfesionService;
import com.cibertec.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private ProfesionService profesionService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    // metodo lista
    @GetMapping
    public String verListaDocentes(Model model) {
        List<Docente> docentes = docenteService.findAll();
        model.addAttribute("docentes", docentes);
        //docenteService.printDocentes(); //ver en consola docentes registrados
        return "docentelista"; 
    }
     
    
    // metodo agregar docente
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("docente", new Docente()); 
        model.addAttribute("profesiones", profesionService.findAll()); 
        return "docenteform"; 
    }

    @PostMapping("/agregar")
    public String agregarDocente(@ModelAttribute Docente docente) {
        
        System.out.println("Nombre: " + docente.getNombres());
        System.out.print("Apellidos" + docente.getApellidos());
        System.out.println("Email: " + docente.getEmail());
        System.out.println("Celular: " + docente.getCelular());
        System.out.println("Profesión: " + docente.getProfesion().getId());
        
        docenteService.save(docente);
        return "redirect:/docentes"; 
    }

  
    // metodo editar 
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Docente> docenteOpt = docenteService.findById(id);
        if (docenteOpt.isPresent()) {
            model.addAttribute("docente", docenteOpt.get());
            model.addAttribute("profesiones", profesionService.findAll()); 
            return "editardocente"; 
        }
        return "redirect:/docentes"; 
    }

    @PostMapping("/editar")
    public String editarDocente(@ModelAttribute Docente docente) {
        docenteService.save(docente);
        return "redirect:/docentes"; 
    }
    
    // metodo eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarDocente(@PathVariable Long id) {
        docenteService.deleteById(id);
        return "redirect:/docentes"; 
    }

    
}

