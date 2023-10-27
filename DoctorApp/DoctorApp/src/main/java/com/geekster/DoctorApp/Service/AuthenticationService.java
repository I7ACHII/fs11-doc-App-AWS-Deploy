package com.geekster.DoctorApp.Service;

import com.geekster.DoctorApp.Model.AuthenticationToken;
import com.geekster.DoctorApp.Model.Patient;
import com.geekster.DoctorApp.Repository.IAuthenticationToken;
import com.geekster.DoctorApp.Repository.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationToken iAuthenticationToken;

    @Autowired
    IPatientRepo iPatientRepo;


    // This method checks if the patient has sign In or not i.e A session is created by this email or not
    public boolean authenticate(String patientEmail, String authToken) {

        AuthenticationToken authentication = iAuthenticationToken.findFirstByTokenValue(authToken);
        if(authentication == null){
            return false;
        }
        if( ! authentication.getPatient().getPatientEmail().equals(patientEmail)){
            return false;
        }
        return true;
    }
}
