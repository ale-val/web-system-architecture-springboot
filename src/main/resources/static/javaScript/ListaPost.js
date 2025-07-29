document.addEventListener("DOMContentLoaded", function (){

    caricaPost(`/listaPost?offset=${offsetPost}`);

    document.getElementById("caricaAltro").addEventListener("click", function () {
        caricaPost(`/listaPost?offset=${offsetPost}`);
    });
});

let offsetPost = 0;
let offsetCommenti = 0;

function caricaPost(url) {
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Problemi alla rete');
            }
            return response.text();
        })
        .then(str => {
            const parser = new DOMParser();
            const xmlDoc = parser.parseFromString(str, "application/xml");

            const posts = Array.from(xmlDoc.getElementsByTagName("post"));

            if(posts.length > 0) {
                offsetPost += posts.length;
            }

            const listaPost = document.getElementById("listaPosts");

            Array.from(posts).forEach(post => {
                const autore = post.getElementsByTagName("utente")[0].textContent;
                const timestamp = post.getElementsByTagName("timestamp")[0].textContent;
                const contenuto = post.getElementsByTagName("contenuto")[0].textContent;

                const li = document.createElement("li");

                const link = document.createElement("a");
                link.href = "#";
                link.addEventListener("click", function () {
                    openModal(post);
                });

                const h3 = document.createElement("h3");
                h3.textContent = `Post di ${autore} - ${new Date(timestamp).toLocaleString()}`;
                const p = document.createElement("p");
                p.textContent = contenuto;

                link.appendChild(h3);
                link.appendChild(p);
                li.appendChild(link);
                listaPost.appendChild(li);
            });
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

function openModal(post) {
    offsetCommenti = 0;
    const finestraModale = document.createElement("div");
    finestraModale.className="finestraModale";

    const contenutoModale = document.createElement("div");
    contenutoModale.className="contenutoModale";

    const closeButton = document.createElement("span");
    closeButton.className = "close";
    closeButton.innerHTML = "&times;";
    closeButton.addEventListener("click", () => document.body.removeChild(finestraModale));

    const titolo = document.createElement("h2");
    titolo.textContent = "Dettagli del Post";

    const username = document.createElement("p");
    username.id = "username";
    username.innerHTML = `<strong>${post.getElementsByTagName("utente")[0].textContent}</strong>`;

    const contenutoPost = document.createElement("p");
    contenutoPost.id = "contenutoPost";
    contenutoPost.textContent = post.getElementsByTagName("contenuto")[0].textContent;

    const titoloCommenti = document.createElement("h2");
    titoloCommenti.textContent = "Commenti";

    const listaCommenti = document.createElement("ul");
    listaCommenti.id = "listaCommenti";
    listaCommenti.innerHTML = "";

    caricaCommenti(post.id)
        .then(xml => {
            costruisciCommenti(xml, listaCommenti);
        });

    const caricaAltriCommenti = document.createElement("button");
    caricaAltriCommenti.id = "caricaAltriCommenti";
    caricaAltriCommenti.textContent = "Carica Commenti";
    caricaAltriCommenti.addEventListener("click", function () {
        caricaCommenti(post.id)
            .then(xml => {
                costruisciCommenti(xml, listaCommenti);
            });
    });

    const formCommenti = document.createElement("form");
    formCommenti.method = "post";
    formCommenti.className = "forms";

    const labelCommento = document.createElement("label");
    labelCommento.htmlFor = "commento";
    labelCommento.textContent = "Commenta:";

    const textArea = document.createElement("textarea");
    textArea.id = "commento";
    textArea.name = "commento";
    textArea.maxLength = 120;
    textArea.required = true;
    textArea.rows = 4;
    textArea.cols = 50;

    const buttonCommenti = document.createElement("button");
    buttonCommenti.type = "submit";
    buttonCommenti.textContent = "Pubblica";

    formCommenti.appendChild(labelCommento);
    formCommenti.appendChild(document.createElement("br"));
    formCommenti.appendChild(textArea);
    formCommenti.appendChild(document.createElement("br"));
    formCommenti.appendChild(buttonCommenti);

    formCommenti.addEventListener("submit", function (e) {
        e.preventDefault();

        const commentoText = textArea.value.trim();

        const nuovoCommento = {
            contenuto: commentoText,
            post_id: post.id,
        };

        fetch('/aggiungiCommento', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuovoCommento)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Errore nell'invio del commento!");
                }
                return response.json();
            })
            .then(commentoAggiunto => {
                const liCommento = document.createElement("li");
                liCommento.innerHTML = `<strong>${commentoAggiunto.utente.nome_utente}</strong>: ${commentoAggiunto.contenuto} (${new Date(commentoAggiunto.creazione).toLocaleString()})`;
                listaCommenti.prepend(liCommento);

                textArea.value = "";
            })
            .catch(function(err) {
                console.error(err);
                alert("Errore nella comunicazione con il server!");
            });
    });

    contenutoModale.appendChild(closeButton);
    contenutoModale.appendChild(titolo);
    contenutoModale.appendChild(username);
    contenutoModale.appendChild(contenutoPost);
    contenutoModale.appendChild(titoloCommenti);
    contenutoModale.appendChild(listaCommenti);
    contenutoModale.appendChild(caricaAltriCommenti);
    contenutoModale.appendChild(formCommenti);

    finestraModale.appendChild(contenutoModale)

    document.body.appendChild(finestraModale);
    finestraModale.style.display = "block";
}

function costruisciCommenti(xml, listaCommenti) {
    const commenti = xml.getElementsByTagName("commento");
    if(commenti.length > 0) {
        offsetCommenti += commenti.length;
    }
    Array.from(commenti).forEach(commento => {
        const autoreCommento = commento.getElementsByTagName("utente")[0].textContent;
        const timestampComm = commento.getElementsByTagName("timestamp")[0].textContent;
        const testoCommento = commento.getElementsByTagName("contenuto")[0].textContent;
        const liCommento = document.createElement("li");
        liCommento.innerHTML = `<strong>${autoreCommento}</strong>: ${testoCommento} (${new Date(timestampComm).toLocaleString()})`;
        listaCommenti.appendChild(liCommento);
    });
}

function caricaCommenti(postId) {
    return fetch(`/${postId}/listaCommenti?offset=${offsetCommenti}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Errore nella risposta del server per i commenti");
            }
            return response.text();
        })
        .then(xmlString => {
            const parser = new DOMParser();
            return parser.parseFromString(xmlString, "application/xml");
        });
}

function openNuovoPostModal() {
    const finestraModale = document.getElementById("finestraModale")
    const contenutoDinamico = document.getElementById("contenutoDinamico")

    fetch("/posts?add")
        .then(response => {
            if (!response.ok) {
                throw new Error("Errore nel caricamento del form");
            }
            return response.text();
        })
        .then(data => {
            contenutoDinamico.innerHTML = data;
            finestraModale.style.display = "block";
        })
        .catch(error => {
            console.error(error);
            alert("Errore nel caricamento del form");
        });

    const closeBtn = document.getElementById("close")

    closeBtn.addEventListener("click", function () {
        finestraModale.style.display = "none";
        contenutoDinamico.innerHTML = "";
    });
}