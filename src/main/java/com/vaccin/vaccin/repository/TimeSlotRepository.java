package com.vaccin.vaccin.repository;

import com.vaccin.vaccin.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    @Query("SELECT t FROM TimeSlot t WHERE t.date = ?1 AND t.time = ?2")
    List<TimeSlot> findByDateTimeCenter(Date date, Time time, Long vaccineCenterId);
}