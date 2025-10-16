package com.ratankumar.gmail.hospitalManagement;

import com.ratankumar.gmail.hospitalManagement.entity.Patient;
import com.ratankumar.gmail.hospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientsTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatientRepository() {
       List<Patient> patientList = patientRepository.findAll();
       System.out.println(patientList);
    }

    @Test
    public void testPatientWithAllAppntmntWithMySQLQry() {
        List<Patient> patient = patientRepository.findAllPatientWithAppointments();
        System.out.println(patient);
    }
}
