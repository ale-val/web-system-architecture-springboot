package com.example.assignment.controller;

import com.example.assignment.classes.NuovoPostForm;
import com.example.assignment.entity.Post;
import com.example.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts", params = {"add"})
    public String getNuovoPostForm(Model model) {
        model.addAttribute("postForm", new NuovoPostForm());
        return "postForm";
    }

    @PostMapping("/nuovo-post")
    public String aggiungiPost(@ModelAttribute("form") NuovoPostForm form) {
        String contenuto = form.getContenuto();
        postService.creaPost(contenuto);
        return "redirect:/home";
    }

    @GetMapping("/listaPost")
    public String listaPost(Model model, @RequestParam(defaultValue = "6") int limit,
    @RequestParam(defaultValue = "0") int offset) {

        Collection<Post> listaPost = postService.getAllPosts(limit, offset);
        model.addAttribute("listaPost", listaPost);
        return "listaPost";
    }
}
