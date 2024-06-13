package com.DoctorManagement.DoctorManagementSystemDemo.Service;

import com.DoctorManagement.DoctorManagementSystemDemo.Exception.GlobalException.DoctorDetailsEmptyException;
import com.DoctorManagement.DoctorManagementSystemDemo.Model.DoctorModel;

public class Validator {
    public Object validDoctorModel;

    public static void validDoctorModel(DoctorModel doctorModel){
        if(doctorModel.getName() == null || doctorModel.getName().isEmpty() || doctorModel.getName().isBlank()){
            throw new DoctorDetailsEmptyException("Doctor name is empty");
        }
        if(doctorModel.getSpecialization() == null || doctorModel.getSpecialization().isEmpty() || doctorModel.getSpecialization().isBlank()){
            throw new DoctorDetailsEmptyException("Doctor specialization is empty");
        }
        if(doctorModel.getContactInfo() == null || doctorModel.getContactInfo().isEmpty() || doctorModel.getContactInfo().isBlank()){
            throw new DoctorDetailsEmptyException("Doctor contact info is empty");
        }
    }
}
