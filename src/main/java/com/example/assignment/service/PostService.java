package com.example.assignment.service;

import com.example.assignment.repository.RepositoryPost;
import com.example.assignment.entity.Post;
import com.example.assignment.entity.Utente;
import com.example.assignment.repository.RepositoryUtente;
import com.example.assignment.security.MyUtenteDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class PostService {
    @Autowired
    private RepositoryPost repositoryPost;

    @Autowired
    private RepositoryUtente repositoryUtente;

    public void creaPost(String contenuto) {
        MyUtenteDetails userDetails = (MyUtenteDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Utente utente = repositoryUtente.getUserByUsername(username);

        Post post = new Post(utente, contenuto);
        repositoryPost.save(post);
    }

    public Collection<Post> getAllPosts(int limit, int offset) {
        return repositoryPost.findPostsWithLimit(limit, offset);
    }
}
