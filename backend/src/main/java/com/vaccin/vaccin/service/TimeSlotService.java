package com.vaccin.vaccin.service;


import com.vaccin.vaccin.dto.TimeSlotCreateDto;
import com.vaccin.vaccin.dto.TimeSlotDto;
import com.vaccin.vaccin.exception.BadRequestException;
import com.vaccin.vaccin.exception.ErrorMessages;
import com.vaccin.vaccin.exception.NotFoundException;
import com.vaccin.vaccin.model.TimeSlot;
import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.repository.TimeSlotRepository;
import com.vaccin.vaccin.repository.VaccineCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotService {

    private TimeSlotRepository timeSlotRepository;

    private VaccineCenterRepository vaccineCenterRepository;

    @Autowired
    public TimeSlotService(TimeSlotRepository timeSlotRepository, VaccineCenterRepository vaccineCenterRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.vaccineCenterRepository = vaccineCenterRepository;
    }

    public TimeSlotDto getTimeSlot(long timeSlotId) throws NotFoundException {
        Optional<TimeSlot> timeSlotOptional = timeSlotRepository.findById(timeSlotId);

        if (timeSlotOptional.isEmpty()) {
            throw new NotFoundException(ErrorMessages.timeSlotNotFound);
        }

        return new TimeSlotDto(timeSlotOptional.get());
    }

    public TimeSlot getTimeSlot(Date date, Time time, VaccineCenter vaccineCenter) {
        List<TimeSlot> timeSlotList = timeSlotRepository.findByDateTimeCenter(date, time, vaccineCenter.getId());

        if (!timeSlotList.isEmpty()) {
            return timeSlotList.get(0);
        }
        return addTimeSlot(date, time, vaccineCenter);
    }

    public TimeSlot addTimeSlot(Date date, Time time, VaccineCenter vaccineCenter) {
        TimeSlot newTimeSlot = new TimeSlot();
        newTimeSlot.setDate(date);
        newTimeSlot.setTime(time);
        newTimeSlot.setVaccineCenter(vaccineCenter);
        newTimeSlot.setNoOfAppointments(0);
        newTimeSlot.setFull(false);

        timeSlotRepository.save(newTimeSlot);

        return newTimeSlot;
    }

    public boolean isTimeSlotAvailable(TimeSlot timeSlot) {
        return timeSlot.getNoOfAppointments() <= 5;
    }

    public TimeSlot getAvailableBoosterTimeSlot(TimeSlot initialTimeSlot) {
        Date initialDate = initialTimeSlot.getDate();
        Time initialTime = initialTimeSlot.getTime();
        int daysBetween = initialTimeSlot.getVaccineCenter().getVaccineType().getDaysBetweenShots();
        Date boosterDate = addDays(initialDate, daysBetween);
        Time boosterTime = Time.valueOf(initialTime.toString());

        TimeSlot boosterTimeSlot = getTimeSlot(boosterDate, boosterTime, initialTimeSlot.getVaccineCenter());
        while (boosterTimeSlot.getFull()) {
            boosterDate = addDays(boosterTimeSlot.getDate(), 1);
            boosterTimeSlot = getTimeSlot(boosterDate, boosterTime, initialTimeSlot.getVaccineCenter());
        }

        return boosterTimeSlot;
    }

    private Date addDays(Date date, int noOfDays) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(date.toString(), formatter)
                .plusDays(noOfDays);

        return Date.valueOf(localDate);
    }

    private Time addHours(Time time, int noOfHours) {
        LocalTime localTime = time.toLocalTime();

        localTime = localTime.plusHours(noOfHours);

        return Time.valueOf(localTime);
    }

    public TimeSlot increaseNoOfAppointments(TimeSlot timeSlot) {
        int noOfAppointments = timeSlot.getNoOfAppointments();
        timeSlot.setNoOfAppointments(++noOfAppointments);
        timeSlot.setFull(noOfAppointments > 5);
        return timeSlot;
    }

    public TimeSlot decreaseNoOfAppointments(TimeSlot timeSlot) {
        int noOfAppointments = timeSlot.getNoOfAppointments();
        timeSlot.setNoOfAppointments(--noOfAppointments);
        timeSlot.setFull(noOfAppointments > 5);
        return timeSlot;
    }

}
