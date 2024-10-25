package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    
}
