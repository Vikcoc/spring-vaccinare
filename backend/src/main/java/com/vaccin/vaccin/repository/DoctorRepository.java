package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
