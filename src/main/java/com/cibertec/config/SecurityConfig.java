package com.cibertec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((requests) -> requests
	            .anyRequest().permitAll()  // Permitir acceso a todas las rutas sin autenticación
	        )
	        .formLogin()
	            .loginPage("/auth/iniciosesion")  // Ruta de tu página de login personalizada
	            .permitAll()  // Permitir acceso a la página de login sin autenticación
	        .and()
	        .logout().permitAll()  // Configura el logout si es necesario
	        .and()
	        .csrf().disable();  // Opcional: deshabilita CSRF si no lo necesitas
	    return http.build();
	}

    


}

