package com.geekster.DoctorApp.Controller;

import com.geekster.DoctorApp.Model.Patient;
import com.geekster.DoctorApp.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("patients")
    List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("patient/signUp")
    public String signUpPatient(@RequestBody Patient patient){
        return patientService.signUpPatient(patient);
    }

    @PostMapping("patient/signIn/{patientEmail}/{PatientPassword}")
    public String signInPatient(@PathVariable String patientEmail, @PathVariable String PatientPassword) {
        return patientService.signInPatient(patientEmail, PatientPassword);
    }

    @DeleteMapping("patient/signOut/{patientEmail}/{authToken}")
    public String signOutPatient(@PathVariable String patientEmail, @PathVariable String authToken){
        return patientService.signOutPatient(patientEmail, authToken);
    }
}
