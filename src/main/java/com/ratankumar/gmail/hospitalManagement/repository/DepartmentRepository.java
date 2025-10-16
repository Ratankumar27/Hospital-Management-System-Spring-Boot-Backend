package com.ratankumar.gmail.hospitalManagement.repository;

import com.ratankumar.gmail.hospitalManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
