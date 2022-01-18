package com.example.sanitasapp.repository;

import com.example.sanitasapp.models.Doctor;
import com.example.sanitasapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByUsername(String username);
    Optional<Doctor> findByFirstName(String firstName);
}
