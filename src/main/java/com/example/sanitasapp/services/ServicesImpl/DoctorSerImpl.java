package com.example.sanitasapp.services.ServicesImpl;

import com.example.sanitasapp.models.Doctor;
import com.example.sanitasapp.models.Patient;
import com.example.sanitasapp.repository.DoctorRepository;
import com.example.sanitasapp.services.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorSerImpl implements DoctorServices {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorSerImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Optional<Doctor> getDoctorByUsername(String username){
        return doctorRepository.findByUsername(username);
    }

    public Optional<Doctor> getDoctorByFirstname(String firstName){
        return doctorRepository.findByFirstName( firstName);
    }

    public void addDoctorToList(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctor(){
        return doctorRepository.findAll();
    }

    public void removeDoctor(Long doctorId){
        doctorRepository.deleteById(doctorId);
    }

}
