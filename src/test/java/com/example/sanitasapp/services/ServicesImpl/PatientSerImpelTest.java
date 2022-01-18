package com.example.sanitasapp.services.ServicesImpl;

import com.example.sanitasapp.models.Patient;
import com.example.sanitasapp.models.Users;
import com.example.sanitasapp.repository.PatientRepository;
import com.example.sanitasapp.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientSerImpelTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientSerImpel patientServices;

    private Patient patient;
    private List<Patient> patientList;

    @BeforeEach
    void setUp() {
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

        patientList = List.of(patient, patient1);
    }

    @Test
    void getPatientByUsername() {
        when(patientRepository.findByUsername(anyString())).thenReturn(Optional.of(patient));

        Optional<Patient> foundUsers = patientServices.getPatientByUsername("user@email.com");

        assertNotNull(foundUsers);
        assertEquals("james@patient.com", foundUsers.get().getUsername());

        verify(patientRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void getPatientById() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.ofNullable(patient));

        patientServices.getPatientById(anyLong());

        verify(patientRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAllPatients() {
        when(patientRepository.findAll()).thenReturn(patientList);

        patientServices.getAllPatients();

        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void addPatientToList() {
        // mock UserRepository
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        //Call the method to be tested
        patientServices.addPatientToList(patient);

        //Make Assertion
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void removePatient() {

        // mock PostRepository

        //Call the method to be tested
        patientServices.removePatient(patient.getPatientId());

        //Make Assertion
        verify(patientRepository, times(1)).deleteById(1L);
    }
}