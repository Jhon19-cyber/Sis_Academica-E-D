package com.cibertec.service;

import com.cibertec.model.Docente;
import com.cibertec.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    public List<Docente> findAll() {
    	
        return docenteRepository.findAll();
    }
    
    // ver en consola -- lista
    public void printDocentes() {
        List<Docente> docentes = findAll();
        docentes.forEach(docente -> System.out.println(docente.getNombres() + " " +
            docente.getApellidos() + 
            (docente.getProfesion() != null ? docente.getProfesion().getDes_profesion() : "Sin profesión")));
    }
    

    public Optional<Docente> findById(Long id) {
        return docenteRepository.findById(id);
    }

    public Docente save(Docente docente) {
        return docenteRepository.save(docente);
    }

    public void deleteById(Long id) {
        docenteRepository.deleteById(id);
    }
}

