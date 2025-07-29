package com.example.assignment.classes;

import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.springframework.context.annotation.*;

@Configuration
public class ThymeleafConfig {
    @Bean
    public ITemplateResolver htmlTemplateResolver() {
        ClassLoaderTemplateResolver htmlResolver = new ClassLoaderTemplateResolver();
        htmlResolver.setPrefix("templates/");
        htmlResolver.setSuffix(".html");
        htmlResolver.setTemplateMode("HTML");
        htmlResolver.setCharacterEncoding("UTF-8");
        htmlResolver.setOrder(1);
        htmlResolver.setCheckExistence(true);
        return htmlResolver;
    }

    @Bean
    public ITemplateResolver xmlTemplateResolver() {
        ClassLoaderTemplateResolver xmlResolver = new ClassLoaderTemplateResolver();
        xmlResolver.setPrefix("templates/");
        xmlResolver.setSuffix(".xml");
        xmlResolver.setTemplateMode("XML");
        xmlResolver.setCharacterEncoding("UTF-8");
        xmlResolver.setOrder(2);
        xmlResolver.setCheckExistence(true);
        return xmlResolver;
    }
}
