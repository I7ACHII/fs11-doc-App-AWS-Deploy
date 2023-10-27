package com.geekster.DoctorApp.Service;

import com.geekster.DoctorApp.Model.AuthenticationToken;
import com.geekster.DoctorApp.Model.Patient;
import com.geekster.DoctorApp.Repository.IAuthenticationToken;
import com.geekster.DoctorApp.Repository.IPatientRepo;
import com.geekster.DoctorApp.Service.EmailHandler.emailHandler;
import com.geekster.DoctorApp.Service.EncryptPassword.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.geekster.DoctorApp.Service.EncryptPassword.EncryptPassword.encryptPassword;

@Service
public class PatientService {

    @Autowired
    IPatientRepo iPatientRepo;

    @Autowired
    IAuthenticationToken iAuthenticationToken;


    public List<Patient> getAllPatients() {
        return iPatientRepo.findAll();
    }

    // If the patient is new, He must sign up
    public String signUpPatient(Patient patient) {

        // checking if the patient has passed a valid email Id or not
        String PatientEmail = patient.getPatientEmail();
        if(PatientEmail == null){
            return "Invalid Email!!";
        }


        // checking if the patient already exist in the database or not
        Patient PatientExist = iPatientRepo.findFirstByPatientEmail(PatientEmail);
        if(PatientExist != null){
            return "Patient already exists by this email!!";
        }


        // If both the above conditions are not match then that means patient is new and he must sign Up
        try {

            // Before storing patient into the database, patient's password should be encrypted for security reasons and then store into the database
            String patientPassword = patient.getPatientPassword();
            String EncryptedPatientPassword = encryptPassword(patientPassword);
            patient.setPatientPassword(EncryptedPatientPassword);
            iPatientRepo.save(patient);
            return "Patient registered successfully!!";

        } catch (NoSuchAlgorithmException e) {
            return "Something went wrong!!";
        }


    }

    public String signInPatient(String patientEmail, String patientPassword)  {

        if(patientEmail == null)
            return "Invalid Email Id!!";

        // Finding patient in the database by patient email and checking whether given patient email Id exists in database or not
        Patient patient = iPatientRepo.findFirstByPatientEmail(patientEmail);
        if(patient == null){
            return "Email not registered!!";
        }

        // Encrypt the given patientPassword so that it can be matched with password in the database

        try {
            String encryptedPassword = encryptPassword(patientPassword);
            if( patient.getPatientPassword().equals(encryptedPassword) ){

                // creating a session
                // Creating a AuthenticationToken object and storing into the AuthenticationToken table in database means a session is created
                AuthenticationToken authenticationToken = new AuthenticationToken(patient);
                iAuthenticationToken.save(authenticationToken);
                emailHandler.sendEmail("pulkitmittal194@gmail.com", "Email Testing", authenticationToken.getTokenValue());
                return "Token has been sent to your email!!";

            }
            else{
                return "Invalid Credentials!!";
            }

        } catch (Exception e) {
            return "Internal Error occurred during Sign In!!";
        }

    }

    public String signOutPatient(String patientEmail, String authToken) {

        // checking if patient exist in the database by patientEmail
        Boolean patientExist = iPatientRepo.existsById(iPatientRepo.findFirstByPatientEmail(patientEmail).getPatientId());
        if(!patientExist){
            return "Patient does not exist by this patientEmail!!";
        }

        // checking if authentication exist in the database by authentication token
        AuthenticationToken authenticationToken = iAuthenticationToken.findFirstByTokenValue(authToken);
        Boolean sessionExist = iAuthenticationToken.existsByTokenValue(authToken);

        if(!sessionExist){
            return "Sign Out not allowed foe unauthenticated user!!";
        }
        iAuthenticationToken.delete(authenticationToken);
        return "Sign out successfully!!";

    }
}
