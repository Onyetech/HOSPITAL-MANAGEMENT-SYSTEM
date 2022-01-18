package com.example.sanitasapp.services.ServicesImpl;

import com.example.sanitasapp.models.Doctor;
import com.example.sanitasapp.models.Patient;
import com.example.sanitasapp.repository.DoctorRepository;
import com.example.sanitasapp.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorSerImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorSerImpl doctorServices;

    private Doctor doctor;
    private List<Doctor> doctorList;

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

        doctorList = List.of(doctor, doctor1);
    }

    @Test
    void getDoctorByUsername() {
        when(doctorRepository.findByUsername(anyString())).thenReturn(Optional.of(doctor));

        Optional<Doctor> foundUsers = doctorServices.getDoctorByUsername("user@email.com");

        assertNotNull(foundUsers);
        assertEquals("james@patient.com", foundUsers.get().getUsername());

        verify(doctorRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void getDoctorByFirstname() {
        when(doctorRepository.findByFirstName(anyString())).thenReturn(Optional.of(doctor));

        Optional<Doctor> foundUsers = doctorServices.getDoctorByFirstname("Henry");

        assertNotNull(foundUsers);
        assertEquals("Henry", foundUsers.get().getFirstName());

        verify(doctorRepository, times(1)).findByFirstName(anyString());
    }

    @Test
    void addDoctorToList() {
        // mock UserRepository
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        //Call the method to be tested
        doctorServices.addDoctorToList(doctor);

        //Make Assertion
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    void getAllDoctor() {
        when(doctorRepository.findAll()).thenReturn(doctorList);

        doctorServices.getAllDoctor();

        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    void removeDoctor() {
        // mock PostRepository

        //Call the method to be tested
        doctorServices.removeDoctor(doctor.getDoctorId());

        //Make Assertion
        verify(doctorRepository, times(1)).deleteById(1L);
    }
}