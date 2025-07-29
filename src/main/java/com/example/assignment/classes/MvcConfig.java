package com.example.assignment.classes;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("Home");
        registry.addViewController("/").setViewName("Home");
        registry.addViewController("/login").setViewName("Login");
        registry.addViewController("/registrazione").setViewName("Registrazione");
    }

}
