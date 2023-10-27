package com.geekster.DoctorApp.Repository;


import com.geekster.DoctorApp.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin, Long> {

}
