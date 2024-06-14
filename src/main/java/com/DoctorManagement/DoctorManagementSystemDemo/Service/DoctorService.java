package com.DoctorManagement.DoctorManagementSystemDemo.Service;

import java.util.List;

import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;
import com.DoctorManagement.DoctorManagementSystemDemo.Model.DoctorModel;

public interface DoctorService {
    public String addDoctor(DoctorModel doctor);
    public List<Doctor> getAllDoctor();
    public Boolean deleteDoctor(Long id);
    public Boolean updateDoctor(Long id,DoctorModel doctorModel);
    public List<DoctorModel> findDoctorById(Long id);
}
