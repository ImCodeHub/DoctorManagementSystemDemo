# `Doctor Management System`

A Spring Boot application for managing doctors, including adding, updating, deleting, and fetching doctor details. follows all the CRUD Operations and primerily focus area of this project is [UNIT TESTING](https://github.com/ImCodeHub/DoctorManagementSystemDemo/blob/main/README.md#testing) with Junit and mockito.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)

## Introduction

The Doctor Management System is a RESTful web service built using Spring Boot. It provides APIs to manage doctor information, including operations like adding, updating, deleting, and fetching doctor details.

## Features

- Add a new doctor
- Update existing doctor details
- Delete a doctor by ID
- Fetch all doctors
- Find a doctor by ID
- Find a doctor by name

## Technologies Used

- Java 11
- Spring Boot 2.7.x
- Spring Data JPA
- H2 Database
- Mockito
- JUnit 5

## Setup and Installation

### Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/ImCodeHub/DoctorManagementSystemDemo.git
   
2. Navigate to the project directory:
   ```bash
    cd doctor-management-system
3. Build the project using Maven:
   ```bash
     mvn clean install
4. Run the application:
   ```bash
    mvn spring-boot:run

Or use [Spring Initializr](https://start.spring.io/) web interface to generate the project.

- *Application Properties:*
  Configure `application.properties` for `H2 database:
  properties`
  spring.datasource.url=jdbc:h2:mem:testdb
  
  spring.datasource.driverClassName=org.h2.Driver
  
  spring.datasource.username=sa
  
  spring.datasource.password=password
  
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  
  spring.h2.console.enabled=true

  *add this dependency for H2 database in your [pom.xml](https://github.com/ImCodeHub/DoctorManagementSystemDemo/blob/main/pom.xml)*
   ```xml
   <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
 	</dependency>
   ```

     ```properties
      spring.application.name=Employee
      # H2 database
      spring.datasource.url=jdbc:h2:mem:testdb
      spring.datasource.driverClassName=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=password
      spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
      spring.h2.console.enabled=true
     ```

Use this link to [http://localhost:8080/h2-console](http://localhost:8080/h2-console) to access H2 inmemory database.


The application will start on http://localhost:8080.

## Usage
You can use tools like [Postman](https://www.postman.com/) to interact with the API endpoints.

![image](https://github.com/ImCodeHub/DoctorManagementSystemDemo/assets/98458146/50e8a2d0-faa4-4e8a-9b70-6cd4280f354b)


## API Endpoints
### Add Doctor
  - URL: `/api/v1/doctor/addDoctor`
  - Method: POST
  - Request Body:
    ```json
    {
    "name": "John Doe",
    "specialization": "Cardiologist",
    "contactInfo": "john.doe@example.com"
    }
  - Response: 200 OK
    
### Get All Doctors
- URL: `/api/v1/doctor/allDoctors`
- Method: GET
- Response: 200 OK
  
### Delete Doctor
- URL: `/api/v1/doctor/delete/{id}`
- Method: DELETE
- Response: 200 OK

### Update Doctor
- URL: `/api/v1/doctor/update/{id}`
- Method: PUT
- Request Body:
   ```json
   {
       "name": "John Doe",
       "specialization": "Cardiologist",
       "contactInfo": "john.doe@example.com"
   }
- Response: 200 OK
  
### Find Doctor by ID
- URL: `/api/v1/doctor/findDoctor/{id}`
- Method: GET
- Response: 200 OK

### Find Doctor by Name
- URL: `/api/v1/doctor/findDcotorByName/{name}`
- Method: GET
- Response: 200 OK
  
 ### API Documentation:
   - Use `Swagger/OpenAPI` for API documentation you can follow this link http://localhost:8080/swagger-ui/index.html#/.
   - Add the `springdoc-openapi-ui` library to the list of your project dependencies (No additional configuration is needed):

      ```xml 
         <dependency>
               <groupId>org.springdoc</groupId>
               <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
               <version>2.5.0</version>
         </dependency>

      ```
      ![image](https://github.com/ImCodeHub/DoctorManagementSystemDemo/assets/98458146/e2a0e3a6-3f5a-42a4-9607-0efe57e5f96c)

## Testing
Unit tests and integration tests are provided using JUnit and Mockito.
the dependency is already available when you initialize you can find this dependency as bellow in [pom.xml](https://github.com/ImCodeHub/DoctorManagementSystemDemo/blob/main/pom.xml)

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-test</artifactId>
   <scope>test</scope>
</dependency>
```
Running Tests
To run the tests, use the following command:
   ```bash
      mvn test
```
You can use VSCode as well for spring boot project and test frame work for Junit and mockito.
![image](https://github.com/ImCodeHub/DoctorManagementSystemDemo/assets/98458146/039a3f51-5fa4-4a74-a5fa-c7cc206579bd)

### Example Unit Test
   To see all `unit test` of this project [click here](https://github.com/ImCodeHub/DoctorManagementSystemDemo/tree/main/src/test)
#### All service Unit test with Juint & Mockito framework

   ```java
package com.DoctorManagement.DoctorManagementSystemDemo.Service;

import com.DoctorManagement.DoctorManagementSystemDemo.Model.DoctorModel;
import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;
import com.DoctorManagement.DoctorManagementSystemDemo.Repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DoctorServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    private DoctorModel doctorModel;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorModel = new DoctorModel("ankit", "doctor", "ankit@gmail.com");
        doctor = new Doctor();
        doctor.setName("ankit");
        doctor.setSpecialization("doctor");
        doctor.setContactInfo("ankit@gmail.com");
    }

    @Test
    void testAddDoctor_Success() {
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);
        String result = doctorService.addDoctor(doctorModel);
        assertThat(result).isEqualTo("Doctor details has been saved.");
    }
}

   ```
### Explanation of Each Section

1. **Introduction**: A brief overview of the project.
2. **Features**: A list of the main features of the application.
3. **Technologies Used**: Technologies and libraries used in the project.
4. **Setup and Installation**: Instructions to set up and run the project locally.
5. **Usage**: How to use the application (mentioning tools like Postman).
6. **API Endpoints**: Detailed documentation of the API endpoints provided by the application.
7. **Testing**: Information about running the tests and an example of a unit test.
8. **Contributing**: Guidelines for contributing to the project.

---
# To contact me:
   - name: Ankit sharma
   - mobile no: 8962780856
   - E-mail id: ankitsharma.as420@gmail.com
   - My [Linked In](https://www.linkedin.com/in/ankit-sharma-a6689b1a5/) Profile.
     
**To see My other projects** [click here](https://github.com/ImCodeHub?tab=repositories)


