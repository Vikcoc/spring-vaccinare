package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.TimeSlotCreateDto;
import com.vaccin.vaccin.dto.VaccineAppointmentCreateDto;
import com.vaccin.vaccin.dto.VaccineAppointmentDto;
import com.vaccin.vaccin.model.TimeSlot;
import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.model.VaccineAppointment;
import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.repository.TimeSlotRepository;
import com.vaccin.vaccin.repository.UserRepository;
import com.vaccin.vaccin.repository.VaccineAppointmentRepository;
import com.vaccin.vaccin.repository.VaccineCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineAppointmentService {

    @Autowired
    private VaccineAppointmentRepository vaccineAppointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private VaccineCenterRepository vaccineCenterRepository;

    public String addAppointment(VaccineAppointmentCreateDto vaccineAppointmentCreateDto) {

        // creez un VaccineAppointment
        VaccineAppointment vaccineAppointment = new VaccineAppointment(vaccineAppointmentCreateDto);

        // caut Userul cu id-ul primit
        Long patientId = vaccineAppointmentCreateDto.getPatientId();
        Optional<User> patientOptional = userRepository.findById(patientId);

        if (patientOptional.isEmpty()) {
            return "User not found";
        }

        // setez Userul pentru appointment
        vaccineAppointment.setPatient(patientOptional.get());

        // scot Date, Time si vaccineCenterId din CreateDto
        String rawDate = vaccineAppointmentCreateDto.getDate();
        String rawTime= vaccineAppointmentCreateDto.getTime();
        Date date = Date.valueOf(rawDate);
        Time time = Time.valueOf(rawTime);
        Long vaccineCenterId = vaccineAppointmentCreateDto.getVaccineCenterId();


        // caut daca exista VaccineCenter cu id-ul corespunzator
        Optional<VaccineCenter> vaccineCenterOptional = vaccineCenterRepository.findById(vaccineCenterId);

        if (vaccineCenterOptional.isEmpty()) {
            return "VaccineCenter not found";
        }

        VaccineCenter vaccineCenter = vaccineCenterOptional.get();

        // caut daca exista un TimeSlot cu date, time si centerId corespunzatoare
        List<TimeSlot> timeSlots = timeSlotRepository.findByDateTimeCenter(date, time, vaccineCenterId);

        if (timeSlots.size() > 1) {
            // problema
            return "Database error";
        }

        if (timeSlots.isEmpty()) {

            // daca nu exista, atunci creez un timeslot la acel centru la acea ora
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setDate(date);
            timeSlot.setTime(time);
            timeSlot.setVaccineCenter(vaccineCenter);
            timeSlot.setNoOfAppointments(1);
            timeSlot.setFull(false);

            // il pun in DB
            timeSlotRepository.save(timeSlot);

            // setez TimeSlot-ul nou creat pentru VaccineAppointment
            vaccineAppointment.setTimeSlot(timeSlot);

            vaccineAppointmentRepository.save(vaccineAppointment);

            return "Added VaccineAppointment (new slot)";
        }

        // daca totusi exista deja TimeSlot-ul
        TimeSlot timeSlot = timeSlots.get(0);

        // daca e full
        if (timeSlot.getFull()) {
            return "TimeSlot full";
        }

        // updatam TimeSlot-ul in baza de date
        long id = timeSlot.getId();
        int noOfAppointments = timeSlot.getNoOfAppointments() + 1;
        boolean full = noOfAppointments > 5;
        timeSlotRepository.updateAppointmentsAndFullById(id, noOfAppointments, full);

        // setam TimeSlot-ul pentru VaccineAppointment-ul meu
        vaccineAppointment.setTimeSlot(timeSlot);

        // il setam ca nefiind finalizat
        vaccineAppointment.setFulfilled(false);

        vaccineAppointmentRepository.save(vaccineAppointment);

        return "Added VaccineAppointment (slot already exists)";
    }
}
