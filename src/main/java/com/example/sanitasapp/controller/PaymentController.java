package com.example.sanitasapp.controller;


import com.example.sanitasapp.models.Payment;
import com.example.sanitasapp.services.AppointmentServices;
import com.example.sanitasapp.services.PaymentServices;
import com.example.sanitasapp.services.ServicesImpl.AppointmentSerImpl;
import com.example.sanitasapp.services.ServicesImpl.PaymentSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class PaymentController {
    private final AppointmentServices appointmentServices;
    private final PaymentServices paymentServices;

    @Autowired
    public PaymentController(AppointmentServices appointmentServices, PaymentServices paymentServices) {
        this.appointmentServices = appointmentServices;
        this.paymentServices = paymentServices;
    }

    @GetMapping("/payAppointment/{appointmentId}")
    public String makePayment(Model model, @PathVariable Long appointmentId, HttpServletRequest request){
        HttpSession session = request.getSession();
        Payment payment = new Payment();
        payment.setStatus("Paid");
        payment.setAmount("$500");
        payment.setDateOfPayment(LocalDateTime.now());
        payment.setAppointment(appointmentServices.getAppointmentById(appointmentId));
        model.addAttribute("card", payment);
        session.setAttribute("card", payment);
        return "redirect:/paymentPage";
    }

    @GetMapping("/paymentPage")
    public String paymentPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        return "make-payment";
    }

    @GetMapping("/makePayment")
    public String takePayment(HttpServletRequest request){
        HttpSession session = request.getSession();
        Payment payment = (Payment) session.getAttribute("card");
        paymentServices.savePayment(payment);
        return "redirect:/viewPatientAppointment";
    }
}
