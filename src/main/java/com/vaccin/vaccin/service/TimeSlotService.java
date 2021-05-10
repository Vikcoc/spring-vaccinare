package com.vaccin.vaccin.service;


import com.vaccin.vaccin.dto.TimeSlotCreateDto;
import com.vaccin.vaccin.model.TimeSlot;
import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.repository.TimeSlotRepository;
import com.vaccin.vaccin.repository.VaccineCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private VaccineCenterRepository vaccineCenterRepository;

    public String addTimeSlot(TimeSlotCreateDto timeSlotCreateDto) {

        TimeSlot timeSlot = new TimeSlot(timeSlotCreateDto);

        timeSlot.setFull(false);

        Long vaccineCenterId = timeSlotCreateDto.getVaccineCenterId();
        Optional<VaccineCenter> vaccineCenterOptional = vaccineCenterRepository.findById(vaccineCenterId);

        if (vaccineCenterOptional.isEmpty()) {
            return "Vaccine Center not found";
        }

        VaccineCenter vaccineCenter = vaccineCenterOptional.get();

        timeSlot.setVaccineCenter(vaccineCenter);

        timeSlotRepository.save(timeSlot);

        return "Added TimeSlot";
    }

}
