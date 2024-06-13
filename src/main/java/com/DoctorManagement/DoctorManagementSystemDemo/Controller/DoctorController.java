package com.DoctorManagement.DoctorManagementSystemDemo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.DoctorManagement.DoctorManagementSystemDemo.Model.DoctorModel;
import com.DoctorManagement.DoctorManagementSystemDemo.Service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("addDoctor")
    public ResponseEntity<String> addDoctorEntity(@RequestBody DoctorModel doctorModel){
        String response = doctorService.addDoctor(doctorModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
