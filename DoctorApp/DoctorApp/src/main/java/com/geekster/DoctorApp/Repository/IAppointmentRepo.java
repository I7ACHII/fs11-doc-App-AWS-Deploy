package com.geekster.DoctorApp.Repository;


import com.geekster.DoctorApp.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {

}
