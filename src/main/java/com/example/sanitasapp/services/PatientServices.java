package com.example.sanitasapp.services;

import com.example.sanitasapp.models.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PatientServices {
    Optional<Patient> getPatientByUsername(String username);

    Optional<Patient> getPatientById(Long patientId);

    List<Patient> getAllPatients();

    void addPatientToList(Patient patient);

    void removePatient(Long patientId);

}
