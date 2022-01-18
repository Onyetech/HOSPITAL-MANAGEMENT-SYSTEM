package com.example.sanitasapp.controller;

import com.example.sanitasapp.models.Appointment;
import com.example.sanitasapp.models.Doctor;
import com.example.sanitasapp.models.Patient;
import com.example.sanitasapp.services.AppointmentServices;
import com.example.sanitasapp.services.DoctorServices;
import com.example.sanitasapp.services.PatientServices;
import com.example.sanitasapp.services.PaymentServices;
import com.example.sanitasapp.services.ServicesImpl.AppointmentSerImpl;
import com.example.sanitasapp.services.ServicesImpl.DoctorSerImpl;
import com.example.sanitasapp.services.ServicesImpl.PatientSerImpel;
import com.example.sanitasapp.services.ServicesImpl.PaymentSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AppointmentController {
    private final AppointmentServices appointmentServices;
    private final DoctorServices doctorServices;
    private final PatientServices patientServices;
    private final PaymentServices paymentService;

    @Autowired
    public AppointmentController(AppointmentServices appointmentServices, DoctorServices doctorServices, PatientServices patientServices, PaymentServices paymentService) {
        this.appointmentServices = appointmentServices;
        this.doctorServices = doctorServices;
        this.patientServices = patientServices;
        this.paymentService = paymentService;
    }

    @PostMapping("/appointmentWeb")
    public String bookAppointmentFromWeb(@ModelAttribute("appointment") Appointment appointment){
        appointmentServices.addAppointment(appointment);
        return "thankyou";
    }

    @GetMapping("/addAppointment")
    public String bookAppointmentFromAdmin(Model model){
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        model.addAttribute("doctorData", doctorServices.getAllDoctor());
        model.addAttribute("patientData", patientServices.getAllPatients());
        return "add-appointment";
    }

    @PostMapping("/addAppointment")
    public String addAppointmentFromAdmin(@ModelAttribute("appointment") Appointment appointment){
        Optional<Patient> patient = patientServices.getPatientByUsername(appointment.getPatientEmail());
        Optional<Doctor> doctor = doctorServices.getDoctorByFirstname(appointment.getDoctor().getFirstName());
        doctor.ifPresent(appointment::setDoctor);
        patient.ifPresent(appointment::setPatient);
        appointmentServices.addAppointment(appointment);
        return "redirect:/overall";
    }

    @GetMapping("/viewAppointment")
    public String viewAppointments(Model model){
        model.addAttribute("allAppointment", appointmentServices.getAllAppointment());
        model.addAttribute("paymentStatus", paymentService);
        return "appointments";
    }

    @GetMapping("/deleteAppointment/{appointmentId}")
    public String deleteAppointment(@PathVariable Long appointmentId){
        paymentService.deletePaymentByAppointmentId(appointmentId);
        appointmentServices.deleteAppointment(appointmentId);
        return "redirect:/viewAppointment";
    }
}
