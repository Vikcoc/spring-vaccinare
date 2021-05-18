package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.dto.VaccineAppointmentDto;
import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.model.VaccineAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface VaccineAppointmentRepository extends JpaRepository<VaccineAppointment, Long> {

    @Query("SELECT v FROM VaccineAppointment v WHERE v.patient = ?1")
    List<VaccineAppointment> findByPatient(User patient);
}
