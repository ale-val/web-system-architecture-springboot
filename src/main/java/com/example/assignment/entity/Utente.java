package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue
    private int utente_id;

    private String nome_utente;
    private String email;
    private String password;
    private String role;

    public Utente(String nome_utente, String email, String password, String role) {
        this.nome_utente = nome_utente;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String toString() {
        return nome_utente + " " + email + " " + password + " " + role;
    }
}
