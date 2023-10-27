package com.geekster.DoctorApp.Repository;


import com.geekster.DoctorApp.Model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationToken extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findFirstByTokenValue(String authToken);

    Boolean existsByTokenValue(String authToken);
}
