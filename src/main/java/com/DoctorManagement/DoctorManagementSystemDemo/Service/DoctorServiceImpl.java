package com.DoctorManagement.DoctorManagementSystemDemo.Service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;
import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorDetailsEmptyException;
// import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorNotFoundException;
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

            logger.info("doctor data saved {}",doctor);
            doctorRepository.save(doctor);

            return "Doctor details has been saved.";
        } else {
            throw new DoctorDetailsEmptyException("doctor details are not found in Object");
        }
    }

}
