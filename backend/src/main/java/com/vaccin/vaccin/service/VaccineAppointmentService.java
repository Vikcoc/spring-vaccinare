package com.vaccin.vaccin.service;

import com.vaccin.vaccin.dto.VaccineAppointmentCreateDto;
import com.vaccin.vaccin.dto.VaccineAppointmentDto;
import com.vaccin.vaccin.exception.*;
import com.vaccin.vaccin.model.TimeSlot;
import com.vaccin.vaccin.model.User;
import com.vaccin.vaccin.model.VaccineAppointment;
import com.vaccin.vaccin.model.VaccineCenter;
import com.vaccin.vaccin.repository.TimeSlotRepository;
import com.vaccin.vaccin.repository.UserRepository;
import com.vaccin.vaccin.repository.VaccineAppointmentRepository;
import com.vaccin.vaccin.repository.VaccineCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineAppointmentService {

    private VaccineAppointmentRepository vaccineAppointmentRepository;
    private UserRepository userRepository;
    private TimeSlotRepository timeSlotRepository;
    private VaccineCenterRepository vaccineCenterRepository;
    private TimeSlotService timeSlotService;

    @Autowired
    public VaccineAppointmentService(VaccineAppointmentRepository vaccineAppointmentRepository,
                                     UserRepository userRepository, TimeSlotRepository timeSlotRepository,
                                     VaccineCenterRepository vaccineCenterRepository, TimeSlotService timeSlotService) {
        this.vaccineAppointmentRepository = vaccineAppointmentRepository;
        this.userRepository = userRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.vaccineCenterRepository = vaccineCenterRepository;
        this.timeSlotService = timeSlotService;
    }

    public List<VaccineAppointmentDto> appointUser(VaccineAppointmentCreateDto vaccineAppointmentCreateDto)
            throws BadRequestException {

        List<VaccineAppointmentDto> vaccineAppointmentDtoList = new ArrayList<>();

        // scot userul din DB
        Optional<User> patientOptional = userRepository.findById(vaccineAppointmentCreateDto.getPatientId());
        if (patientOptional.isEmpty()) {
            throw new BadRequestException(ErrorMessages.userNotFound);
        }
        User patient = patientOptional.get();
        if (patient.getAppointed()) {
            throw new BadRequestException(ErrorMessages.userIsAppointed);
        }

        // scot vaccine centerul din DB
        Optional<VaccineCenter> vaccineCenterOptional
                = vaccineCenterRepository.findById(vaccineAppointmentCreateDto.getVaccineCenterId());
        if (vaccineCenterOptional.isEmpty()) {
            throw new BadRequestException(ErrorMessages.centerNotFound);
        }
        VaccineCenter vaccineCenter = vaccineCenterOptional.get();

        // scot timeslotul din DB
        Date date = Date.valueOf(vaccineAppointmentCreateDto.getDate());
        Time time = Time.valueOf(vaccineAppointmentCreateDto.getTime());
        TimeSlot initialTimeSlot = timeSlotService.getTimeSlot(date, time, vaccineCenter);
        if (initialTimeSlot.getFull()) {
            throw new BadRequestException(ErrorMessages.fullTimeSlot);
        }

        // TimeSlotul este ok
        // programarea initiala
        VaccineAppointment initialAppointment = addAppointment(patient, initialTimeSlot, null);

        // programarea rapelului
        TimeSlot boosterTimeSlot = timeSlotService.getAvailableBoosterTimeSlot(initialTimeSlot);

        VaccineAppointment boosterAppointment = addAppointment(patient, boosterTimeSlot, initialAppointment);

        // cele doua timesloturi trebuie updatate in DB
        initialTimeSlot = timeSlotService.increaseNoOfAppointments(initialTimeSlot);
        timeSlotRepository.save(initialTimeSlot);
        boosterTimeSlot = timeSlotService.increaseNoOfAppointments(boosterTimeSlot);
        timeSlotRepository.save(initialTimeSlot);

        // userul trebuie updatat in DB
        patient.setAppointed(true);
        userRepository.save(patient);

        vaccineAppointmentDtoList.add(new VaccineAppointmentDto(initialAppointment));
        vaccineAppointmentDtoList.add(new VaccineAppointmentDto(boosterAppointment));
        return vaccineAppointmentDtoList;
    }

    private VaccineAppointment addAppointment(User user, TimeSlot timeslot, VaccineAppointment initialAppointment) {
        VaccineAppointment vaccineAppointment = new VaccineAppointment();
        vaccineAppointment.setPatient(user);
        vaccineAppointment.setTimeSlot(timeslot);
        vaccineAppointment.setFulfilled(false);
        vaccineAppointment.setInitialAppointment(initialAppointment);

        return vaccineAppointmentRepository.save(vaccineAppointment);
    }

    public List<VaccineAppointmentDto> getAppointments(long patientId) throws NotFoundException {

        Optional<User> patientOptional = userRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new NotFoundException(ErrorMessages.userNotFound);
        }

        User patient = patientOptional.get();

        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentRepository.findByPatient(patient);
        List<VaccineAppointmentDto> vaccineAppointmentDtos
                = vaccineAppointments
                .stream()
                .map(VaccineAppointmentDto::new)
                .collect(Collectors.toList());

        return vaccineAppointmentDtos;
    }

    public void deleteAppointments(Long patientId) throws BadRequestException {
        Optional<User> patientOptional = userRepository.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new BadRequestException(ErrorMessages.userNotFound);
        }
        User patient = patientOptional.get();

        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentRepository.findByPatient(patient);

        if (vaccineAppointments.isEmpty()) {
            throw new BadRequestException(ErrorMessages.userNotAppointed);
        }

        // daca nu sunt toate programarile in viitor
        if (!vaccineAppointments.stream().allMatch
                (va -> va.getTimeSlot()
                        .getDate().toLocalDate().compareTo(LocalDate.now()) < 0)) {
            throw new BadRequestException(ErrorMessages.appointmentPassed);
        }

        for (var appointment : vaccineAppointments) {
            // il sterg din DB
            vaccineAppointmentRepository.delete(appointment);
            // updatez timeslotul

            TimeSlot timeSlot = appointment.getTimeSlot();
            timeSlot = timeSlotService.decreaseNoOfAppointments(timeSlot);
            timeSlotRepository.save(timeSlot);

            patient.setAppointed(false);
            userRepository.save(patient);
        }
    }

    public VaccineAppointmentDto fulfillAppointment(long appointmentId) throws BadRequestException {

        Optional<VaccineAppointment> vaccineAppointmentOptional = vaccineAppointmentRepository.findById(appointmentId);
        if (vaccineAppointmentOptional.isEmpty()) {
            throw new BadRequestException(ErrorMessages.appointmentNotFound);
        }

        VaccineAppointment vaccineAppointment = vaccineAppointmentOptional.get();

        if (vaccineAppointment.getFulfilled()) {
            throw new BadRequestException(ErrorMessages.appointmentAlreadyMarkedFulfilled);
        }

        Date appointmentDate = vaccineAppointment.getTimeSlot().getDate();
        Time appointmentTime = vaccineAppointment.getTimeSlot().getTime();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentDateTime = appointmentDate.toLocalDate()
                .atTime(appointmentTime.toLocalTime());

        if (appointmentDateTime.isAfter(now)) {
            throw new BadRequestException(ErrorMessages.appointmentNotPassed);
        }

        vaccineAppointment.setFulfilled(true);
        return new VaccineAppointmentDto(vaccineAppointmentRepository.save(vaccineAppointment));
    }
}
