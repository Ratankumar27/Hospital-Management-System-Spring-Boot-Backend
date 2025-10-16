package com.ratankumar.gmail.hospitalManagement.repository;

import com.ratankumar.gmail.hospitalManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
