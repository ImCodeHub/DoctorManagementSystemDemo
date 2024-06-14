package com.DoctorManagement.DoctorManagementSystemDemo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;
import com.DoctorManagement.DoctorManagementSystemDemo.Model.DoctorModel;
import com.DoctorManagement.DoctorManagementSystemDemo.Service.DoctorService;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;




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
    
    @GetMapping("allDoctors")
    public ResponseEntity<List<Doctor>> getDoctors(){
        List<Doctor> list = doctorService.getAllDoctor();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Long id){
        Boolean response = doctorService.deleteDoctor(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Boolean> updateDoctor(@PathVariable Long id, @RequestBody DoctorModel doctorModel){
        Boolean response = doctorService.updateDoctor(id, doctorModel);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("findDoctor/{id}")
    public ResponseEntity<List<DoctorModel>> findDoctorById(@PathVariable Long id){
        List<DoctorModel> doctor = doctorService.findDoctorById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
 
}
