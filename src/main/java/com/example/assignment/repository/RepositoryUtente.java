package com.example.assignment.repository;

import com.example.assignment.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RepositoryUtente extends CrudRepository<Utente, Long> {
    @Query("SELECT u FROM Utente u WHERE u.nome_utente = :nome_utente")
    Utente getUserByUsername(@Param("nome_utente") String nome_utente);

    @Query("SELECT u FROM Utente u WHERE u.nome_utente = :username or u.email = :email")
    Utente findUserByUsernameEmail(@Param("username") String username, @Param("email") String email);
}