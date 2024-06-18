package com.DoctorManagement.DoctorManagementSystemDemo.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;

import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;
import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorDetailsEmptyException;
import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorNotFoundException;
import com.DoctorManagement.DoctorManagementSystemDemo.Model.DoctorModel;
import com.DoctorManagement.DoctorManagementSystemDemo.Repository.DoctorRepository;

public class DoctorServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    private DoctorModel doctorModel;

    private Doctor doctorObj;

    // close the all unwanted class or resourses when the enitre test complete its
    // excution.
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        doctorModel = new DoctorModel("ankit", "doctor", "ankit@gmail.com");
        doctorObj = new Doctor("abhi", "doctor", "abhi@gmail.com");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testAddDoctor_Success() {

        mock(DoctorModel.class);
        Doctor doctor = new Doctor();
        doctor.setName("ankit");
        doctor.setSpecialization("doctor");
        doctor.setContactInfo("ankit@gmail.com");

        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        String result = doctorService.addDoctor(doctorModel);
        assertThat(result).isEqualTo("Doctor details has been saved.");
    }

    @Test
    void testAddDoctor_NullModel() {
        assertThrows(DoctorDetailsEmptyException.class, () -> {
            doctorService.addDoctor(null);
        });
    }

    @Test
    void testDeleteDoctor() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setName("abhinav");

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

        Boolean result = doctorService.deleteDoctor(doctorId);
        assertThat(result).isTrue();
        verify(doctorRepository, times(1)).deleteById(doctorId);
    }

    @Test
    void testDeleteDoctor_NotFound() {
        Long doctorId = 1L;
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());

        assertThrows(DoctorNotFoundException.class, () -> {
            doctorService.deleteDoctor(doctorId);
        });
    }

    @Test
    void testFindDoctorById() {
        Long doctorId= 1L;
        Doctor doctor = new Doctor();

        doctor.setName("john");
        doctor.setSpecialization("doctor");
        doctor.setContactInfo("123654");

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

        List<DoctorModel> doctorModels = doctorService.findDoctorById(doctorId);
        assertThat(doctorModels).hasSize(1);
        assertThat(doctorModels.get(0).getName()).isEqualTo("john");
    }

    @Test
    void testFindDoctorById_NotFound(){
        Long doctorId = 1L;
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());
        assertThrows(DoctorNotFoundException.class, () ->{
            doctorService.findDoctorById(doctorId);
        });
    }

    @Test
    void testFindDoctorByName() {
        String docName = "ankit";

        Doctor doctor = new Doctor();
        doctor.setName(docName);
        doctor.setSpecialization("doctor");
        doctor.setContactInfo("123456");

        when(doctorRepository.findDcotorByName(docName)).thenReturn(Optional.of(doctor));

        List<DoctorModel> doctorModels = doctorService.findDoctorByName(docName);

        assertThat(doctorModels).hasSize(1);
        assertThat(doctorModels.get(0).getName()).isEqualTo("ankit");
    }

    @Test
    void testFindDoctorByName_NotFound(){
        String docName = "ankit";
        when(doctorRepository.findDcotorByName(docName)).thenReturn(Optional.empty());
        assertThrows(DoctorNotFoundException.class,() ->{
            doctorService.findDoctorByName(docName);
        });
    }

    @Test
    void testGetAllDoctor() {
        List<Doctor> list = new ArrayList<>();
        Doctor doctor1 = new Doctor("ankit", "doctor", "ankit@gmail.com");
        Doctor doctor2 = new Doctor("abhi", "doctor", "abhi@gmail.com");

        list.add(doctor1);
        list.add(doctor2);

        when(doctorRepository.findAll()).thenReturn(list);

        List<Doctor> docList = doctorService.getAllDoctor();
        assertThat(docList).hasSize(2);
    }

    @Test
    void testUpdateDoctor() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setName("arun");

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        Boolean result = doctorService.updateDoctor(doctorId, doctorModel);
        assertThat(result).isTrue();
        assertThat(doctor.getName()).isEqualTo("ankit");
        assertThat(doctor.getSpecialization()).isEqualTo("doctor");
        assertThat(doctor.getContactInfo()).isEqualTo("ankit@gmail.com");

    }

    @Test
    void testUpdateDoctor_NotFound(){
        Long doctorId = 1L;

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());
        assertThrows(DoctorNotFoundException.class, () -> {
            doctorService.updateDoctor(doctorId, doctorModel);
        });
    }
}
