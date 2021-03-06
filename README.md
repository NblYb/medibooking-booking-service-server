# Medibooking Booking Service Server
## Tech Stack:
* SpringBoot
* Spring Security + JWT
* Docker
* PostgreSQL
* Lombok
* Mapstruct
* Spring Data JPA
* H2 in-memory DB
* MockMvc
* Flyway
* Rabbitmq

## Topics
1. [How to run this application](#How-to-run-this-application)
2. [How to access the spring boot restful application](#How-to-access-the-spring-boot-restful-application)
3. [Database structure](#Database-structure)
4. [Advantages of this application](#Advantages-of-this-application)

### This is the springBoot based RESTful API. It supports the below functions:

* Application support H2 in-mem DB and Postgres DB, by default postgres DB is being used. If you want to use H2 in-mem DB, application-h2.properties shall be used.
* Create an appointment
* Cancel an appointment
* Get all the appointments of a patient given doctor name and date
* Get all the appointments of a doctor or a patient
* Create an patient
* Get doctor/patient by his/her ID
* Change doctor/patient personal info
* Get all the doctors
* Get all the languages
* Get all the specialization
* Once the appointment booking completed, the appointment detail will be sent to a Rabbitmq queue for email service to retrieve.

## How to run this application

* Navigate the the root folder under the command line
* Run the command to build the whole project: **gradle clean build**

## How to access the spring boot restful application
### Application by default provide 40 doctor users and 40 patient users, an example is provided below for both patient and doctor user:
#### Doctor user example: username/password : doctor.Xu@gmail.com/password (Can access doctor user centre)
#### Patient user example: username/password : patient.Jonathan@gmail.com/password (Only patient can make an appointment. & Can access patient user centre)

## Database structure

![Database-strucutre](booking-service-db-structure.png "Database-strucutre")

## Advantages of this application
* Hibernate builds the entity layer to connect PostgreSQL database server
* Flyway prepared the initial data.
* Spring Data JPA builds the repository layer (DAO) & H2 in-mem database used as Unit Test to test this layer
* Mapstruct used to map data between entities and dtos
* MockMvc unit test service and controller layer
* Lombok makes our life easierIt automatically generates getter,setter, constructor, hashcode, log etc.
* Dockerfile utilize multi stage and put application files into docker instead of using fat jar.
* Utilize Spring security to implement authentication and authorization. 
* Docker compose has been used in local to easily set up the local environment.
