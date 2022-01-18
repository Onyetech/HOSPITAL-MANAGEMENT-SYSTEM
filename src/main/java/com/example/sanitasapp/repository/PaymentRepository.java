package com.example.sanitasapp.repository;

import com.example.sanitasapp.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment getPaymentByAppointmentAppointmentId(Long appointmentId);
    int deleteByAppointmentAppointmentId(Long appointmentId);
}
