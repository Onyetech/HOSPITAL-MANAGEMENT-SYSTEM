package com.example.sanitasapp.services;

import com.example.sanitasapp.models.Payment;
import org.springframework.stereotype.Service;


public interface PaymentServices {

    Payment getPaymentByAppointmentId(Long appointmentId);

    void deletePaymentByAppointmentId(Long appointmentId);

    void savePayment(Payment payment);

}
