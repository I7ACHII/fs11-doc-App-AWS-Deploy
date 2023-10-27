package com.geekster.DoctorApp.Repository;


import com.geekster.DoctorApp.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {

}
