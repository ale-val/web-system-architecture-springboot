package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter @NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private int post_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    private String contenuto;

    @Column(insertable = false, updatable = false)
    private Timestamp creazione;

    public Post(Utente utente, String contenuto) {
        this.utente = utente;
        this.contenuto = contenuto;
    }

    public Post(int post_id, Utente utente, String contenuto, Timestamp creazione) {
        this.post_id = post_id;
        this.utente = utente;
        this.contenuto = contenuto;
        this.creazione = creazione;
    }

    @Override
    public String toString() {
        return "Post [post_id=" + post_id + ", utente_id=" + utente.toString() + ", contenuto=" + contenuto + "]";
    }
}
