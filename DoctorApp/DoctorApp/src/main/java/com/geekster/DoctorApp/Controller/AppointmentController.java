package com.geekster.DoctorApp.Controller;

import com.geekster.DoctorApp.Model.Appointment;
import com.geekster.DoctorApp.Model.Patient;
import com.geekster.DoctorApp.Service.AppointmentService;
import com.geekster.DoctorApp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("appointments")
    List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @PostMapping("appointment/{patientEmail}/{authToken}")
    public String scheduleAppointment(@RequestBody Appointment appointment, @PathVariable String patientEmail, @PathVariable String authToken){

        // In if statement, it is checked whether the patient with patientEmail and authToken has signed in or not
        if(authenticationService.authenticate(patientEmail, authToken)){
            return appointmentService.scheduleAppointment(appointment);

        }
        return "Authentication failed!!";
    }

    @DeleteMapping("appointment/{patientEmail}/{authToken}")
    public String cancelAppointment(@RequestBody Appointment appointment, @PathVariable String patientEmail, @PathVariable String authToken){
        if(authenticationService.authenticate(patientEmail, authToken)){
            return appointmentService.cancelAppointment(appointment);
        }
        return "Appointment cancel is failed due to some invalid Authentication!!";
    }
}
