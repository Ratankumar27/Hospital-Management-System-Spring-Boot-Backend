package com.ratankumar.gmail.hospitalManagement.service;

import com.ratankumar.gmail.hospitalManagement.entity.Insurance;
import com.ratankumar.gmail.hospitalManagement.entity.Patient;
import com.ratankumar.gmail.hospitalManagement.repository.InsuranceRepository;
import com.ratankumar.gmail.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Not found patient by id " + patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);  //bidirectiona; consistency maintainence

        return patient;

    }

    @Transactional
     public Patient disAssociateInsurance(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(null);
        return patient;
    }
}
