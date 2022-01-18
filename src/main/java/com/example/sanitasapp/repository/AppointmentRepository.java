package com.example.sanitasapp.repository;

import com.example.sanitasapp.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    int deleteByPatientPatientId(Long patientId);

    int deleteByDoctorDoctorId(Long doctorId);

    List<Appointment> findAllByPatientPatientId(Long patientId);
}
