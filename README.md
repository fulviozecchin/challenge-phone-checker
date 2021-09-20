# challenge-phone-checker

Valida correttamente numeri di cellulare del Sudafrica

*Read this in other languages: [English](README.EN.md).*

# Indice

- [Come iniziare](#come-iniziare)
- [Come contribuire](#come-contribuire)
- [Manutenzione](#manutenzione)
- [Licenza](#licenza)

# Come iniziare
L'applicazione permette di validare numeri di telefono nel formato Sudafricano (27812738127 formato corretto).  
Offre la possibilità di validare puntualmente un numero inserito maualmente, oppure di validare massivamente
più numeri, mediate l'upload di un file (formati supportati .xlsx, .xls, .csv).  
La validazione manuale, al click del bottone "Valida" (della sezione manuale), mostrerà il risultato in una nuova pagina.  
La validazione massiva, al click del bottone "Valida" (della sezione massiva), genererà due file in formato .xlsx
contenenti i risultati della validazione del file caricato.  
> il file FileCorrectNumbers.xlsx conterrà tutti i numeri corretti e modificati rendendoli corretti  
> il file FileWrongNumbers.xlsx conterrà tutti i numeri che non erano nel formato corretto, quindi errati

## Dipendenze
L'applicazione è una web-application scritta in Java, con l'ausilio di framework e tool come Spring boot e
Maven.  
Per poterla avviare quindi è necessario avere Java installato sul proprio computer (versione 8 o superiori).
## Come installare
L'applicazione è strutturata come una Spring Boot Application, pertanto l'installazione e il suo utilizzo sono molto rapidi.  
Essendo un progetto Maven, una volta scaricato il codice nella workspace desiderata, il progetto andrà importato come
**"Existing Maven Project"**.  
Maven si occuperà di recuperare e scaricare tutte le dipendenze necessarie al funzionamento.
Una volta importato, si dovrà semplicemente startare la classe **PhoneCheckerApplication.java** come
"Spring Boot Application" se si utilizza l'IDE Spring Tool Suite, o come "Java Application" se si utilizza
l'IDE Eclipse.  
Una volta avviata, possiamo recarci tramite browser web all'indirizzo *http://localhost:8080/*
e visualizzare la schermata corretta dell'applicazione.

```js
console.log("Questo è un esempio di blocco di codice")
```

## Documentazione
### Link a documentazione esterna 

# Come contribuire

## Installare le dipendenze di sviluppo

## Struttura del progetto

## Community

### Code of conduct

### Responsible Disclosure

### Segnalazione bug e richieste di aiuto

# Manutenzione 

# Licenza 

## Licenza generale 

## Autori e Copyright

## Licenze software dei componenti di terze parti

