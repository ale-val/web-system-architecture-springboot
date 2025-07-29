package com.example.assignment.controller;

import com.example.assignment.service.CommentService;
import com.example.assignment.entity.Commenti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{post_id}/listaCommenti")
    public String getCommentsByPostId(@PathVariable int post_id, Model model,
                                      @RequestParam(defaultValue = "5") int limit,
                                      @RequestParam(defaultValue = "0") int offset) {
        Collection<Commenti> listaCommenti = commentService.getCommenti(post_id, limit, offset);
        model.addAttribute("listaCommenti", listaCommenti);
        return "listaCommenti";
    }

    @PostMapping("/aggiungiCommento")
    @ResponseBody
    public Commenti aggiungiCommento(@RequestBody Commenti commento) {
        return commentService.creaCommento(commento);
    }
}
