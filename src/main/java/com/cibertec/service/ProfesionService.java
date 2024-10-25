package com.cibertec.service;

import com.cibertec.model.Profesion;
import com.cibertec.repository.ProfesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesionService {

    @Autowired
    private ProfesionRepository profesionRepository;

    public List<Profesion> findAll() {
        return profesionRepository.findAll();
    }

    public Optional<Profesion> findById(Long id) {
        return profesionRepository.findById(id);
    }

    public Profesion save(Profesion profesion) {
        return profesionRepository.save(profesion);
    }

    public void deleteById(Long id) {
        profesionRepository.deleteById(id);
    }
}

