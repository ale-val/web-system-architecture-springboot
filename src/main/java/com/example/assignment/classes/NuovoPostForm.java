package com.example.assignment.classes;

import lombok.*;
import java.sql.Timestamp;

@Getter @Setter @NoArgsConstructor
public class NuovoPostForm {
    private int utente_id;
    private String contenuto;
    private Timestamp creazione;
}
