package com.example.sanitasapp.services;

import com.example.sanitasapp.models.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AppointmentServices {
    void addAppointment(Appointment appointment);

    List<Appointment> getAllAppointment();

    List<Appointment> getAllAppointmentByPatientId(Long patientId);

    void deleteUsingPatientId(Long patientId);

    void deleteUsingDoctorId(Long doctorId);

    void deleteAppointment(Long appointmentId);

    Appointment getAppointmentById(Long appointmentId);
}
