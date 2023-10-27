package com.geekster.DoctorApp.Service;

import com.geekster.DoctorApp.Model.Appointment;
import com.geekster.DoctorApp.Model.Doctor;
import com.geekster.DoctorApp.Repository.IAppointmentRepo;
import com.geekster.DoctorApp.Repository.IDoctorRepo;
import com.geekster.DoctorApp.Repository.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepo iAppointmentRepo;

    @Autowired
    IDoctorRepo iDoctorRepo;

    @Autowired
    IPatientRepo iPatientRepo;

    public List<Appointment> getAllAppointments() {
        return iAppointmentRepo.findAll();
    }

    public String scheduleAppointment(Appointment appointment) {

        // extracting out doctor details out of Appointment object
        Long doctorId = appointment.getDoctor().getDoctorId();
        Boolean doctorExist = iDoctorRepo.existsById(doctorId);

        // extracting out doctor details out of Appointment object
        Long patientId = appointment.getPatient().getPatientId();
        Boolean patientExist = iPatientRepo.existsById(patientId);

        if(doctorExist && patientExist){
            appointment.setAppointmentCreationTime(LocalDateTime.now());
            iAppointmentRepo.save(appointment);
            return "Appointment has been scheduled!!";
        }
        return "Something went wrong while scheduling appointment!!";
    }

    public String cancelAppointment(Appointment appointment) {

        // checking If appointment exist in the database by this appointmentId or not
        Long appointmentId = appointment.getAppointmentId();
        Boolean appointmentExist = iAppointmentRepo.existsById(appointmentId);

        if(!appointmentExist){
            return "Appointment does not exist by this Id!!";
        }

        // checking if Doctor is registered by this doctorId or not
        Long doctorId = appointment.getDoctor().getDoctorId();
        Boolean doctorExist = iDoctorRepo.existsById(doctorId);

        if(! doctorExist){
            return "Doctor does not exist by this doctorId!!";
        }

        // checking if Patient is registered by this patientId or not
        Long patientId = appointment.getPatient().getPatientId();
        Boolean patientExist = iPatientRepo.existsById(patientId);

        if(! patientExist){
            return "Patient does not exist by this patientId!!";
        }

        // Deleting appointment
        iAppointmentRepo.deleteById(appointmentId);
        return "Appointment has been deleted!!";

    }
}
