package com.DoctorManagement.DoctorManagementSystemDemo.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;
import com.DoctorManagement.DoctorManagementSystemDemo.Model.DoctorModel;
import com.DoctorManagement.DoctorManagementSystemDemo.Service.DoctorService;

public class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    private DoctorModel doctorModel;
    private Doctor doctor;

    private AutoCloseable closeable;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorModel = new DoctorModel("ankit", "doctor", "ankit@gmail.com");
        doctor = new Doctor();
        doctor.setName("ankit");
        doctor.setSpecialization("doctor");
        doctor.setContactInfo("ankit@gmail.com");

    }

    @AfterEach
    void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    @Test
    void testAddDoctorEntity() {
        when(doctorService.addDoctor(any(DoctorModel.class))).thenReturn("Doctor details has been saved.");
        ResponseEntity<String> response = doctorController.addDoctorEntity(doctorModel);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Doctor details has been saved.");
    }

    @Test
    void testDeleteDoctor() {
        when(doctorService.deleteDoctor(anyLong())).thenReturn(true);
        ResponseEntity<Boolean> response = doctorController.deleteDoctor(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    void testFindDocotorByName() {
        when(doctorService.findDoctorByName(anyString())).thenReturn(Arrays.asList(doctorModel));
        ResponseEntity<List<DoctorModel>> response = doctorController.findDocotorByName("ankit");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getName()).isEqualTo("ankit");
    }

    @SuppressWarnings("null")
    @Test
    void testFindDoctorById() {
        when(doctorService.findDoctorById(anyLong())).thenReturn(Arrays.asList(doctorModel));
        ResponseEntity<List<DoctorModel>> response = doctorController.findDoctorById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getName()).isEqualTo("ankit");
    }

    @SuppressWarnings("null")
    @Test
    void testGetDoctors() {
        when(doctorService.getAllDoctor()).thenReturn(Arrays.asList(doctor));
        ResponseEntity<List<Doctor>> response = doctorController.getDoctors();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getName()).isEqualTo("ankit");
    }

    @Test
    void testUpdateDoctor() {
        when(doctorService.updateDoctor(anyLong(), any(DoctorModel.class))).thenReturn(true);
        ResponseEntity<Boolean> response = doctorController.updateDoctor(1L, doctorModel);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();
    }
}
