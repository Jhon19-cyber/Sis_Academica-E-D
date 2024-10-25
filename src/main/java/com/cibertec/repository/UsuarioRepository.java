package com.cibertec.repository;

import com.cibertec.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	// llamar el id del usuario
	Usuario findByUsername(String username);

}

