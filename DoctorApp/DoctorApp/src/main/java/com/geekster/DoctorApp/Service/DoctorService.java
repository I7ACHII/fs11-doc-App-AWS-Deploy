package com.geekster.DoctorApp.Service;

import com.geekster.DoctorApp.Model.Doctor;
import com.geekster.DoctorApp.Repository.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    IDoctorRepo iDoctorRepo;
    public String addDoctor(Doctor doctor) {
        iDoctorRepo.save(doctor);
        return "Doctor has been added!!";
    }

    public List<Doctor> getAllDoctors() {
        return iDoctorRepo.findAll();
    }
}
