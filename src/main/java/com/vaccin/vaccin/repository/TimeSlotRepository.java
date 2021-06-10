package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    @Query("SELECT t FROM TimeSlot t WHERE t.date = ?1 AND t.time = ?2 AND t.vaccineCenter.id = ?3")
    List<TimeSlot> findByDateTimeCenter(Date date, Time time, Long vaccineCenterId);

    @Transactional
    @Modifying
    @Query("UPDATE TimeSlot t SET t.noOfAppointments = ?2, t.full = ?3 WHERE t.id = ?1")
    void updateAppointmentsAndFullById(Long id, Integer noOfAppointments, Boolean full);
}
