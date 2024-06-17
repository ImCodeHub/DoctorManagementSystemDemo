package com.DoctorManagement.DoctorManagementSystemDemo.Repository;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;

@DataJpaTest
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @BeforeEach
    void setUp() {
        Doctor doctor = new Doctor("ravi", "doctor", "ram@gmail.com");
        doctorRepository.save(doctor);
    }

    @AfterEach
    void tearDown() {
        Doctor doctor = null;
        doctorRepository.deleteAll();
    }

    // Test case SUCCESS.
    @Test
    public void testFindDoctorByName_Found() {
        String name = "ravi";
        Optional<Doctor> doctorOpt = doctorRepository.findDcotorByName(name);
        assertThat(doctorOpt.isPresent()).isTrue();
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            assertThat(doctor.getName()).isEqualTo(name);

        }

    }

    // Test case FAILURE
    @Test
    public void testFindDoctorByName_NotFound() {
        String name = "abhi";
        Optional<Doctor> doctorOpt = doctorRepository.findDcotorByName(name);
        assertThat(doctorOpt.isEmpty()).isTrue();
    }

}
