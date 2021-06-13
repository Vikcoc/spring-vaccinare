package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.model.VaccineAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;


public interface VaccineAppointmentRepository extends JpaRepository<VaccineAppointment, Long> {

    @Query("SELECT v FROM VaccineAppointment v WHERE v.patient = ?1")
    List<VaccineAppointment> findByPatient(User patient);

    @Query("SELECT v FROM VaccineAppointment v WHERE v.initialAppointment.id = ?1")
    Optional<VaccineAppointment> findBoosterByInitialId(Long initialAppointmentId);
}
