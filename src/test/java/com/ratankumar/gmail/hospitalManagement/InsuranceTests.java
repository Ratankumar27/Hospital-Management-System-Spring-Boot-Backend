package com.ratankumar.gmail.hospitalManagement;

import com.ratankumar.gmail.hospitalManagement.entity.Insurance;
import com.ratankumar.gmail.hospitalManagement.entity.Patient;
import com.ratankumar.gmail.hospitalManagement.repository.InsuranceRepository;
import com.ratankumar.gmail.hospitalManagement.service.InsuranceService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTests {

   @Autowired
   private InsuranceService insuranceService;


    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder().policyNumber("HDFC_1234").provider("HDFC").validUntil(LocalDate.of(2030, 07, 27)).build();
        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);
    }

    @Test
    public void testDisassociateInsurance() {
        Insurance insurance = Insurance.builder().policyNumber("HDFC_1234").provider("HDFC").validUntil(LocalDate.of(2030, 07, 27)).build();
        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);

        Patient updatedPatient = insuranceService.disAssociateInsurance(1L);
        System.out.println(updatedPatient);

    }
}
