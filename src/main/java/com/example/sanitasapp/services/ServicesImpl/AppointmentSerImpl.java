package com.example.sanitasapp.services.ServicesImpl;

import com.example.sanitasapp.models.Appointment;
import com.example.sanitasapp.repository.AppointmentRepository;
import com.example.sanitasapp.services.AppointmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentSerImpl implements AppointmentServices {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentSerImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void addAppointment(Appointment appointment){
        appointmentRepository.save(appointment);
    }


    public List<Appointment> getAllAppointment(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAllAppointmentByPatientId(Long patientId){
        return appointmentRepository.findAllByPatientPatientId(patientId);
    }

    public void deleteUsingPatientId(Long patientId){
        appointmentRepository.deleteByPatientPatientId(patientId);
    }

    public void deleteUsingDoctorId(Long doctorId){
        appointmentRepository.deleteByDoctorDoctorId(doctorId);
    }

    public void deleteAppointment(Long appointmentId){
        appointmentRepository.deleteById(appointmentId);
    }

    public Appointment getAppointmentById (Long appointmentId){
       return appointmentRepository.findById(appointmentId).get();
    }
}
