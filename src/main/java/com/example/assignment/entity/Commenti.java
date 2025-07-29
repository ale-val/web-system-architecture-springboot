package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter @NoArgsConstructor
public class Commenti {
    @Id
    @GeneratedValue
    private int commento_id;

    private int post_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    private String contenuto;
    private Timestamp creazione;

    public Commenti(int post_id, Utente utente, String contenuto, Timestamp creazione) {
        this.post_id = post_id;
        this.utente = utente;
        this.contenuto = contenuto;
        this.creazione = creazione;
    }

    @Override
    public String toString() {
        return "Commenti [commento_id=" + commento_id + ", post_id=" + post_id + ", utente_id=" + utente.toString() + ", contenuto=" + contenuto + ", creazione=" + creazione + "]";
    }
}

