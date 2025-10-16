package com.ratankumar.gmail.hospitalManagement.service;

import com.ratankumar.gmail.hospitalManagement.entity.Appointment;
import com.ratankumar.gmail.hospitalManagement.entity.Doctor;
import com.ratankumar.gmail.hospitalManagement.entity.Patient;
import com.ratankumar.gmail.hospitalManagement.repository.AppointmentRepository;
import com.ratankumar.gmail.hospitalManagement.repository.DoctorRepository;
import com.ratankumar.gmail.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long PatientId) {
        Patient patient = patientRepository.findById(PatientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have diff patients");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);  // maintaining bidirectional

        appointmentRepository.save(appointment);
        return appointment;
    }

    @Transactional
    public Appointment reAssignAppointToDiffDoctr(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);

        doctor.getAppointments().add(appointment);  // bidirectional

        return appointment;

    }
}
