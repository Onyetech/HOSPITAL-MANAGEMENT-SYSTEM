package com.example.sanitasapp.repository;

import com.example.sanitasapp.models.Patient;
import com.example.sanitasapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUsername(String username);
}
