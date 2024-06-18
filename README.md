# `Doctor Management System`

A Spring Boot application for managing doctors, including adding, updating, deleting, and fetching doctor details. follows all the CRUD Operations and primerily focus area of this project is UNIT TESTING with Junit and mockito.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

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

The application will start on http://localhost:8080.

## Usage
You can use tools like Postman to interact with the API endpoints.

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
  
## Testing
Unit tests and integration tests are provided using JUnit and Mockito.

Running Tests
To run the tests, use the following command:
   ```bash
      mvn test
```

### Example Unit Test
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


