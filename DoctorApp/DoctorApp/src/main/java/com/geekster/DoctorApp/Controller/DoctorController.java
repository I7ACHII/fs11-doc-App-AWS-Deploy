package com.geekster.DoctorApp.Controller;


import com.geekster.DoctorApp.Model.Doctor;
import com.geekster.DoctorApp.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("doctor")
    String addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("doctors")
    List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }
}
