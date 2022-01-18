package com.example.sanitasapp.services;

import com.example.sanitasapp.models.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface DoctorServices {
    Optional<Doctor> getDoctorByUsername(String username);

    Optional<Doctor> getDoctorByFirstname(String firstName);

    void addDoctorToList(Doctor doctor);

    List<Doctor> getAllDoctor();

    void removeDoctor(Long doctorId);
}
