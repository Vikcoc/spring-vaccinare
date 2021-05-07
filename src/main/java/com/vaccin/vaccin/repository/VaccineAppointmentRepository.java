package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.model.VaccineAppointment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VaccineAppointmentRepository extends JpaRepository<VaccineAppointment, Long> {
}
