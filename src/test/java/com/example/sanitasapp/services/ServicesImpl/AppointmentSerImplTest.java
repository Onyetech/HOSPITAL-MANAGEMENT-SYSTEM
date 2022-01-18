package com.example.sanitasapp.services.ServicesImpl;

import com.example.sanitasapp.models.Appointment;
import com.example.sanitasapp.models.Doctor;
import com.example.sanitasapp.models.Patient;
import com.example.sanitasapp.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentSerImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentSerImpl appointmentServices;

    private Appointment appointment;
    private List<Appointment> appointmentList;
    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setUp() {
        doctor = Doctor.builder()
                .doctorId(1L)
                .firstName("Henry")
                .lastName("clark")
                .username("james@patient.com")
                .email("james@email.com")
                .password("admin")
                .address("Redtown")
                .gender("male")
                .phone("098755567")
                .Specialization("Neurology")
                .Status("active")
                .build();

        Doctor doctor1 = Doctor.builder()
                .doctorId(2L)
                .firstName("Sarah")
                .lastName("Ted")
                .username("ted@patient.com")
                .email("ted@email.com")
                .password("admin")
                .address("Redtown")
                .gender("female")
                .phone("098755567")
                .Status("active")
                .Specialization("Cardiology")
                .build();

        patient = Patient.builder()
                .patientId(1L)
                .firstName("Henry")
                .lastName("clark")
                .username("james@patient.com")
                .email("james@email.com")
                .password("admin")
                .address("Redtown")
                .gender("male")
                .phone("098755567")
                .Status("active")
                .treatment("pain")
                .build();

        Patient patient1 = Patient.builder()
                .patientId(2L)
                .firstName("Sarah")
                .lastName("Ted")
                .username("ted@patient.com")
                .email("ted@email.com")
                .password("admin")
                .address("Redtown")
                .gender("female")
                .phone("098755567")
                .Status("active")
                .treatment("pain")
                .build();

        appointment = Appointment.builder()
                .appointmentId(1L)
                .patient(patient)
                .doctor(doctor)
                .message("pain")
                .department("Dentist")
                .dateOfAppointment("12-12-2021")
                .phone("098656789")
                .timeOfMeeting("12:00 PM")
                .status("inactive")
                .build();

        Appointment appointment1 = Appointment.builder()
                .appointmentId(2L)
                .patient(patient1)
                .doctor(doctor1)
                .message("pain")
                .department("Neurology")
                .dateOfAppointment("15-12-2021")
                .phone("098656789")
                .timeOfMeeting("11:00 PM")
                .status("active")
                .build();

        appointmentList = List.of(appointment, appointment1);

    }

    @Test
    void addAppointment() {
        // mock UserRepository
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        //Call the method to be tested
        appointmentServices.addAppointment(appointment);

        //Make Assertion
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    void getAllAppointment() {

        when(appointmentRepository.findAll()).thenReturn(appointmentList);

        appointmentServices.getAllAppointment();

        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    void deleteUsingPatientId() {
        //Call the method to be tested
        appointmentServices.deleteUsingPatientId(patient.getPatientId());

        //Make Assertion
        verify(appointmentRepository, times(1)).deleteByPatientPatientId(1L);
    }

    @Test
    void deleteUsingDoctorId() {
        //Call the method to be tested
        appointmentServices.deleteUsingDoctorId(doctor.getDoctorId());

        //Make Assertion
        verify(appointmentRepository, times(1)).deleteByDoctorDoctorId(1L);
    }

    @Test
    void deleteAppointment() {

        //Call the method to be tested
        appointmentServices.deleteAppointment(appointment.getAppointmentId());

        //Make Assertion
        verify(appointmentRepository, times(1)).deleteById(1L);
    }
}