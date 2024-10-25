package com.cibertec.controller;

import com.cibertec.model.Usuario;
import com.cibertec.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/iniciosesion")
    public String showLoginPage(Model model) {
        return "iniciosesion"; 
    }

    // metodo para el login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String contrasena, RedirectAttributes redirectAttributes, HttpSession session) {
        System.out.println("Intento de inicio de sesión: ");
        System.out.println("Usuario: " + username);
        System.out.println("Contraseña: " + contrasena);

        Usuario usuario = usuarioService.findByUsername(username);
        
        if (usuario != null) {
            if (usuario.getContrasena().equals(contrasena)) {
                System.out.println("Inicio de sesión exitoso para el usuario: " + username);
                session.setAttribute("username", username); // guardar nombre de usuario que ingreso
                return "redirect:/auth/home"; 
            } else {
                System.out.println("Contraseña incorrecta para el usuario: " + username);
                redirectAttributes.addFlashAttribute("error", "Contraseña incorrecta");
            }
        } else {
            System.out.println("Usuario no encontrado: " + username);
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
        }
        
        return "redirect:/auth/iniciosesion"; 
    }

    
    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        // obtener nombre del usuario que ingreso
        String username = (String) session.getAttribute("username");

        // mensaje de bienvenida
        if (username != null) {
            model.addAttribute("welcomeMessage", "Bienvenido al sistema: " + username);
        } else {
            model.addAttribute("welcomeMessage", "Bienvenido al sistema:");
        }

        return "home"; 
    }
}


