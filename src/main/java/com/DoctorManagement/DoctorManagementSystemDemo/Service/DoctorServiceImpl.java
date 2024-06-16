package com.DoctorManagement.DoctorManagementSystemDemo.Service;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;
import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorDetailsEmptyException;
import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorNotFoundException;
import com.DoctorManagement.DoctorManagementSystemDemo.Model.DoctorModel;
import com.DoctorManagement.DoctorManagementSystemDemo.Repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public String addDoctor(DoctorModel doctorModel) {
        Doctor doctor = new Doctor();
        if (doctorModel != null) {
            Validator.validDoctorModel(doctorModel);
            doctor.setName(doctorModel.getName());
            doctor.setSpecialization(doctorModel.getSpecialization());
            doctor.setContactInfo(doctorModel.getContactInfo());

            logger.info("doctor data saved {}", doctor);
            doctorRepository.save(doctor);

            return "Doctor details has been saved.";
        } else {
            throw new DoctorDetailsEmptyException("doctor details are not found in Object");
        }
    }

    @Override
    public List<Doctor> getAllDoctor() {
        List<Doctor> list = new ArrayList<>();

        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors != null) {
            for (Doctor doctor : doctors) {
                list.add(doctor);
            }
            return list;
        } else {
            throw new DoctorNotFoundException("Doctor list not found");
        }
    }

    @Override
    public Boolean deleteDoctor(Long id) {
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isPresent()) {
            doctorRepository.deleteById(id);
            return true;
        } else {
            logger.error("doctor not found by this id {}", id);
            throw new DoctorNotFoundException("Doctor not found by this Id - " + id);
        }
    }

    @Override
    public Boolean updateDoctor(Long id, DoctorModel doctorModel) {
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isPresent()) {
            Doctor doctor = optional.get();
            doctor.setName(doctorModel.getName());
            doctor.setSpecialization(doctorModel.getSpecialization());
            doctor.setContactInfo(doctorModel.getContactInfo());

            doctorRepository.save(doctor);
            return true;
        }
        logger.error("Doctor not found by this Id {}", id);
        throw new DoctorNotFoundException("Doctor not found by this Id - " + id);
    }

    @Override
    public List<DoctorModel> findDoctorById(Long id) {
        List<DoctorModel> list = new ArrayList<>();
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isPresent()) {
            Doctor doctor = optional.get();
            DoctorModel doctorModel = new DoctorModel();
            doctorModel.setName(doctor.getName());
            doctorModel.setSpecialization(doctor.getSpecialization());
            doctorModel.setContactInfo(doctor.getContactInfo());

            list.add(doctorModel);
            return list;
        }
        throw new DoctorNotFoundException("Doctor not found by this Id - " + id);
    }

    @Override
    public List<DoctorModel> findDoctorByName(String name) {
        List<DoctorModel> list = new ArrayList<>();
        Optional<Doctor> optionals = doctorRepository.findDcotorByName(name);
        if(optionals.isPresent()){
            Doctor doctor = optionals.get();
            DoctorModel doctorModel = new DoctorModel();
            doctorModel.setName(doctor.getName());
            doctorModel.setSpecialization(doctor.getSpecialization());
            doctorModel.setContactInfo(doctor.getContactInfo());

            list.add(doctorModel);
            return list;
        }

        
        throw new DoctorNotFoundException("Doctor not found by this Id - " + name);
    }

}
