package com.example.assignment.service;

import com.example.assignment.repository.RepositoryComment;
import com.example.assignment.entity.Commenti;
import com.example.assignment.entity.Utente;
import com.example.assignment.repository.RepositoryUtente;
import com.example.assignment.security.MyUtenteDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.Collection;

@Service
@Transactional
public class CommentService {

    @Autowired
    private RepositoryComment repositoryComment;

    @Autowired
    private RepositoryUtente repositoryUtente;

    public Collection<Commenti> getCommenti(int post_id, int limit, int offset) {
        return repositoryComment.findByPostId(post_id, limit, offset);
    }

    public Commenti creaCommento(Commenti commento) {
        MyUtenteDetails userDetails = (MyUtenteDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Utente utente = repositoryUtente.getUserByUsername(username);

        commento.setUtente(utente);
        commento.setCreazione(new Timestamp(System.currentTimeMillis()));

        return repositoryComment.save(commento);
    }
}
