package com.DoctorManagement.DoctorManagementSystemDemo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DoctorManagement.DoctorManagementSystemDemo.Entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>  {
    public Optional<Doctor> findDcotorByName(String name);
}
