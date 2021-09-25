# challenge-phone-checker

Valid South African mobile phone numbers correctly

# Contents

- [How to start](#how-to-start)
- [How to contribute](#how-to-contribute)
- [License](#license)

# How to start
The application allows you to validate phone numbers in South African format (27812738127 correct format).    
It offers the possibility of validating a single number, typed manually,
or of massively validating several numbers by uploading a file (supported formats .csv, xls, .xlsx).  
In case of *Manual validation*, when clicking the "Validate" button (in the manual section), it will show the result in a new page.    
In case of *Massively validation * when clicking the "Validate" button (in the massive section), it will generate two .xlsx files
which contain the result of validation of uploaded file.  
* *FileCorrectNumbers.xlsx* will contains all correct and modified numbers    
* *FileWrongNumbers.xlsx* will contains all wrong numbers  

## Dependecies
The application is a web-application written in Java, with the help of frameworks and tools such as Spring boot and Maven.  
 
To be able to start it, you need to have Java installed on your computer (version 8 or higher).  

## How to install  
The application is structured as a Spring Boot Application, so installation and use are very quick.   
As it is a Maven project, once the code has been downloaded into the desired workspace, the project will be imported as
**"Existing Maven Project"**.    
Maven will retrieve and download all the dependencies necessary for running.   
once imported, simply launch the **PhoneCheckerApplication.java** class as a simple Java application.  
Once launched, we can go to *http://localhost:8080/* via web browser and start using the application.  

## Documentation
Here links of used tools official documentations:  
* [Java](https://docs.oracle.com/en/java/ "Java Documentation")   
* [Maven](https://maven.apache.org/guides/ "Maven Documentation")    
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/ "Spring Boot Documentation")    
* [Thymeleaf](https://www.thymeleaf.org/documentation.html "Thymeleaf Documentation")    
* [H2 Database](https://www.h2database.com/html/main.html "H2 Documentation")  
* [Apache POI](https://poi.apache.org/apidocs/index.html "Apache POI Documentation")  
* [jQuery](https://api.jquery.com/ "jQuery Documentation")  

# How to contribute
Any contribution for improvement is welcomed. Downloading and working with the application is very simple.   
By following the guide on [How to install](#how-to-install) you will be able to work with the application right away.    
Maven will download the dependencies needed for each layer to work, including the
H2 database (which is configured by default as a temporary database in memory).   

## Install development dependecies
As already mentioned above, all development dependencies will be automatically downloaded and installed
by Maven as soon as we import the project into our workspace.  

## Project Structure
The project is structured according to the principles of good programming and best practices, such as the separation of responsibilities.  
In path *com.phonechecker* we can find relative packages for any application layer.  
* *controller*: contains the application controllers    
* *model*: contains the application models    
* *repository*: contains the application repositories for database relation    
* *service*: contains the classes, which provide to manage the application business logic (services)    
* *utils*: contains the *helper* classes, which help services classes   

To display the data, in the H2 database, once the application has been started,
you can go to the path *http://localhost:8080/h2*.  
By default configuration it is a temporary in menory database, which means that each time the application is stopped,
all data in it will be deleted.   
The default value to be entered in the JDBC URL field will then be: *jdbc:h2:mem:testdb*.      
In **application.properties** file there are all configurations about H2 (included the jdbc link, username and password which will be set as follows):   
* User Name: sa  
* Password:    

# License 
The licence for this project is **Apache License, Version 2.0**  

## Authors and Copyright
*Fulvio Zecchin*  
fulviozecchin3@gmail.com

## Software license of third-party components
All the tools and support frameworks used in the application are distributed under an **Open Source** license.  
