package com.ratankumar.gmail.hospitalManagement.repository;

import com.ratankumar.gmail.hospitalManagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
