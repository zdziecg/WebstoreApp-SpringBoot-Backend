# WebstoreApp-SpringBoot-Backend
Beckend part of the web application of the full stack webstore. 
The frontend part is available in the link: https://github.com/zdziecg/WebstoreApp-Angular-Frontend

## Live Demo Full Stack Websore: https://zdziecg-webstore.herokuapp.com/#/
This is a Single Page Appliaction using Spring Boot 2 and Angular 9.

## REST API that allows the following functionalities:

- User registration requiring confirmation of e-mail.
- User can login the site.
- Order menagment
- Products catalogue.
- The user has access to his orders.
- User can logout after completing.

## Technology Stacks

- Java 12
- Spring Boot 2.2.1.
- Spring Security
- Basic Authentication
- Spring Data JPA
- Hibernate
- H2-Database
- Thymeleaf
- Maven
- Swagger-Cli

## Run the Project

1. If you want to use the full stack of application on one port,
run the command: ng build --prod --aot --outputHashing=all
in the frontend part of the application.
2. Run mvn clean.
3. Run mvn install.
4. Maven-resources-plugin automatically copy all files from Angular static folder to src/target/classes/static folder of Spring Boot project.
5. Run mvn spring-boot:run.
6. Spring Boot will import data into database by executing import.sql automatically.
7. The backend server is running on localhost:7776.
8. The full stack webstore web application is running on localhost:7776 (If you have taken step number 1).

## API Documentation
Swagger UI: http://localhost:7776/swagger-ui.html#/
## Database
H2 Database: http://localhost:7776/h2-console
