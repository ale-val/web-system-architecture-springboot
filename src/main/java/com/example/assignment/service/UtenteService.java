package com.example.assignment.service;

import com.example.assignment.entity.Utente;
import com.example.assignment.repository.RepositoryUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UtenteService {
    @Autowired
    private RepositoryUtente repositoryUtente;

    public void registraUtente(String username, String password, String email) {
        if (repositoryUtente.findUserByUsernameEmail(username, email) != null) {
            throw new IllegalArgumentException();
        }

        Utente nuovoUtente = new Utente(username, email, password, "USER");
        repositoryUtente.save(nuovoUtente);
    }
}
