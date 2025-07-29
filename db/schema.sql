# Nome DB: db_assignment

CREATE TABLE utente (
    utente_id INT AUTO_INCREMENT PRIMARY KEY,
    nome_utente VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE post (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    utente_id INT NOT NULL,
    contenuto TEXT NOT NULL,
    creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utente_id) REFERENCES utente(utente_id) ON DELETE CASCADE
);

CREATE TABLE commenti (
    commento_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    utente_id INT NOT NULL,
    contenuto TEXT NOT NULL,
    creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (utente_id) REFERENCES utente(utente_id) ON DELETE CASCADE
);