# challenge-phone-checker

Valida correttamente numeri di cellulare del Sudafrica

*Read this in other languages: [English](README.EN.md).*

# Indice

- [Come iniziare](#come-iniziare)
- [Come contribuire](#come-contribuire)
- [Licenza](#licenza)

# Come iniziare
L'applicazione permette di validare numeri di telefono nel formato Sudafricano (27812738127 formato corretto).  
Offre la possibilità di validare puntualmente un numero inserito maualmente, oppure di validare massivamente
più numeri, mediate l'upload di un file (formati supportati .xlsx, .xls, .csv).  
La validazione manuale, al click del bottone "Valida" (della sezione manuale), mostrerà il risultato in una nuova pagina.  
La validazione massiva, al click del bottone "Valida" (della sezione massiva), genererà due file in formato .xlsx
contenenti i risultati della validazione del file caricato.  
* *FileCorrectNumbers.xlsx* conterrà tutti i numeri corretti e modificati rendendoli corretti  
* *FileWrongNumbers.xlsx* conterrà tutti i numeri che non erano nel formato corretto, quindi errati

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
ed iniziare ad utilizzare l'applicazione.

## Documentazione
Di seguito i link alle documentazioni ufficiali degli strumenti presenti nel progetto:  
* [Java](https://docs.oracle.com/en/java/ "Java Documentation")   
* [Maven](https://maven.apache.org/guides/ "Maven Documentation")    
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/ "Spring Boot Documentation")    
* [Thymeleaf](https://www.thymeleaf.org/documentation.html "Thymeleaf Documentation")    
* [H2 Database](https://www.h2database.com/html/main.html "H2 Documentation")  
* [Apache POI](https://poi.apache.org/apidocs/index.html "Apache POI Documentation")  
* [jQuery](https://api.jquery.com/ "jQuery Documentation")  

# Come contribuire
Ogni contributo migliorativo sarà ben accetto. Scaricare e lavorare con l'applicazione è molto semplice.  
Seguendo la guida su [Come installare](#come-installare) di potrà lavorare da subito con l'applicazione.  
Maven si occuperà di scaricare le dipendenze necessarie per il funzionamento di ogni layer, compreso il database
H2 (che è configurato in questo caso come database temporaneo in memoria).  

## Installare le dipendenze di sviluppo
Come già detto poco sopra, tutte le dipendenze di sviluppo saranno scaricate ed installare automaticamente
da Maven, non appena importeremo il progetto nel nostro workspace.  

## Struttura del progetto

# Licenza 

## Licenza generale 

## Autori e Copyright

## Licenze software dei componenti di terze parti

