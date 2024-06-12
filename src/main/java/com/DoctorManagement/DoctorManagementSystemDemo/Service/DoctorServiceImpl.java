package com.DoctorManagement.DoctorManagementSystemDemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;
import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorNotFoundException;
import com.DoctorManagement.DoctorManagementSystemDemo.Model.DoctorModel;
import com.DoctorManagement.DoctorManagementSystemDemo.Repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public String addDoctor(DoctorModel doctorModel) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorModel.getName());
    }
    
}
