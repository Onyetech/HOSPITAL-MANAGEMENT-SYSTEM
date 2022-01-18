package com.example.sanitasapp.services.ServicesImpl;

import com.example.sanitasapp.models.Appointment;
import com.example.sanitasapp.models.Doctor;
import com.example.sanitasapp.models.Patient;
import com.example.sanitasapp.models.Payment;
import com.example.sanitasapp.repository.PatientRepository;
import com.example.sanitasapp.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentSerImplTest {
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentSerImpl paymentServices;

    private Payment payment;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
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
                .patient(patient1)
                .doctor(doctor1)
                .message("pain")
                .department("Dentist")
                .dateOfAppointment("12-12-2021")
                .phone("098656789")
                .timeOfMeeting("12:00 PM")
                .status("inactive")
                .build();

        payment = Payment.builder()
                .paymentId(1L)
                .amount("$500")
                .appointment(appointment)
                .build();
    }

    @Test
    void getPaymentByAppointmentId() {
        when(paymentRepository.getPaymentByAppointmentAppointmentId(anyLong())).thenReturn(payment);

        paymentServices.getPaymentByAppointmentId(anyLong());

        verify(paymentRepository, times(1)).getPaymentByAppointmentAppointmentId(anyLong());
    }

    @Test
    void deletePaymentByAppointmentId() {
        //Call the method to be tested
        paymentServices.deletePaymentByAppointmentId(appointment.getAppointmentId());

        //Make Assertion
        verify(paymentRepository, times(1)).deleteByAppointmentAppointmentId(1L);
    }

    @Test
    void savePayment() {
        // mock UserRepository
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        //Call the method to be tested
        paymentServices.savePayment(payment);

        //Make Assertion
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }
}