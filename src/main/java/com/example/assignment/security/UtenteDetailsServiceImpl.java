package com.example.assignment.security;

import com.example.assignment.entity.Utente;
import com.example.assignment.repository.RepositoryUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UtenteDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private RepositoryUtente repositoryUtente;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = repositoryUtente.getUserByUsername(username);
        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato");
        }
        return new MyUtenteDetails(utente);
    }
}
