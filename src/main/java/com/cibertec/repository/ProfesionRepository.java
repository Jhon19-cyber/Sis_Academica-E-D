package com.cibertec.repository;

import com.cibertec.model.Profesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionRepository extends JpaRepository<Profesion, Long> {
    
}

