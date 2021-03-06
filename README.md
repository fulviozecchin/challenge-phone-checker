# challenge-phone-checker

Questo baby-progetto nasce come code challenge richiesta da una società per valutare il livello di pensiero nell'affrontare una sfida appunto, in poco tempo e con pochi requisiti.
Si voleva costruire da zero un'applicazione che validasse i numeri di telefono nel formato sudafricano.
Un utente può quindi collegarsi ad una semplice pagina web ed inserire manualmente il numero da validare, oppure caricare un file (.csv, .xlsx) per una validazione massiva.
Indipendentemente dal metodo scelto, qualora il numero non fosse corretto, l'applicazione proverà a segnalare suggerimenti utili e/o autocorrettivi (ad esempio se si è scordato il prefisso +27).
Qualora invece il numero inserito (o un record in caso massivo) non fosse valido, né aspirante tale neppure correggendolo, l'applicazione informerà della sua invalidità, e del motivo per cui il numero è stato scartato.


*Sono assolutamente consapevole che il codice presente in questo progetto poteva essere pensato e scritto (molto) meglio; tuttavia è doveroso sottolineare che questa challenge è stata fatta in 5 giorni, dopo 10-12 ore di lavoro giornaliere (tra lavoro ed università), utilizzando quel poco di tempo (e di fantasia) che mi rimaneva.  
Certamente avrei potuto modificarla e renderla migliore nel tempo, dopo la sua consegna, ma avrebbe perso il suo obiettivo principale: la veridicità e l'autenticità di un lavoro fatto volutamente in breve tempo, in un contesto del tutto nuovo, da zero.*



*Read this in other languages: [English](README.EN.md).*

# Indice

- [Come iniziare](#come-iniziare)
- [Come contribuire](#come-contribuire)
- [Licenza](#licenza)

# Come iniziare
L'applicazione permette di validare numeri di telefono nel formato Sudafricano (27812738127 formato corretto).  
Offre la possibilità di validare puntualmente un numero inserito maualmente, oppure di validare massivamente
più numeri, mediate l'upload di un file (formati supportati .xlsx, .xls, .csv).  
**Il formato corretto del file per la validazione massiva è nella root folder con il nome "Massive Check File.xlsx"**.  
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
Il progetto è strutturato seguendo i principi di buona programmazione, come la separazione delle responsabilità.  
Nel path *com.interlogicatest.phonechecker* troviamo i package per ogni layer applicativo.  
* *controller*: contiene i controller dell'applicazione  
* *model*: contiene il model dell'applicazione  
* *repository*: contiene i repository dell'applicazione per interfacciarsi con il database  
* *service*: contiene quelle classi che si occupano della logica di business dell'applicazione (i service appunto)  
* *utils*: contiene quelle classi 'Helper' di supporto per i i service.  

Per la visualizzazione dei dati, nel database H2, una volta startata l'applicazione,
è possibile recarsi al path *http://localhost:8080/h2*.  
Da configurazione è un db temporanaeo in memoria, questo vuol dire che ad ogni stop dell'applicazione,
tutti i dati presenti in esso saranno cancellati.  
Il valore di default da inserire nel campo JDBC URL, sarà quindi: *jdbc:h2:mem:testdb*.    
Nel file **application.properties** sono presenti le configurazioni relative ad H2 (compresi username
e password, che saranno impostati di default come segue):  
* User Name: sa  
* Password:    

# Licenza 
La licenza per questo progetto è di tipo **Apache License, Version 2.0**  

## Autori e Copyright
*Fulvio Zecchin*  
fulviozecchin3@gmail.com

## Licenze software dei componenti di terze parti
Tutti gli strumenti ed i framework di ausilio utilizzati dall'applicazione, sono distribuiti con licenza **Open Source**.  
