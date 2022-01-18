package com.example.sanitasapp.services.ServicesImpl;

import com.example.sanitasapp.models.Patient;
import com.example.sanitasapp.repository.PatientRepository;
import com.example.sanitasapp.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientSerImpel implements PatientServices {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientSerImpel(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }



    public Optional<Patient> getPatientByUsername(String username){
        return patientRepository.findByUsername(username);
    }

    public Optional<Patient> getPatientById(Long patientId){
        return patientRepository.findById(patientId);
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public void addPatientToList(Patient patient){
        patientRepository.save(patient);
    }

    public void removePatient(Long patientId){
        patientRepository.deleteById(patientId);
    }
}
