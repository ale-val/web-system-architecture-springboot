package com.example.assignment.controller;

import com.example.assignment.classes.RegisterForm;
import com.example.assignment.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("/registrazione")
    public String mostraFormRegistrazione(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "Registrazione";
    }

    @PostMapping("/registrazione")
    public String registraNuovoUtente(@ModelAttribute("registerForm") RegisterForm registerForm, Model model) {
        String username = registerForm.getUsername();
        String email = registerForm.getEmail();
        String password = registerForm.getPassword();

        try {
            utenteService.registraUtente(username, password, email);
            model.addAttribute("registerMessage", "Registrazione effettuata con successo");
            return "Login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Email o Username in uso");
            return "Registrazione";
        }
    }
}
