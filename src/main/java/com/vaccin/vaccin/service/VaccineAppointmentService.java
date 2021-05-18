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
import java.util.stream.Collectors;

@Service
public class VaccineAppointmentService {

    private VaccineAppointmentRepository vaccineAppointmentRepository;
    private UserRepository userRepository;
    private TimeSlotRepository timeSlotRepository;
    private VaccineCenterRepository vaccineCenterRepository;

    @Autowired
    public VaccineAppointmentService(VaccineAppointmentRepository vaccineAppointmentRepository,
                                     UserRepository userRepository, TimeSlotRepository timeSlotRepository,
                                     VaccineCenterRepository vaccineCenterRepository) {
        this.vaccineAppointmentRepository = vaccineAppointmentRepository;
        this.userRepository = userRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.vaccineCenterRepository = vaccineCenterRepository;
    }

    public String addAppointment(VaccineAppointmentCreateDto vaccineAppointmentCreateDto) {

        Optional<User> patientOptional = userRepository.findById(vaccineAppointmentCreateDto.getPatientId());
        Optional<VaccineCenter> vaccineCenterOptional = vaccineCenterRepository.findById(vaccineAppointmentCreateDto.getVaccineCenterId());
        if (patientOptional.isEmpty()) {
            return "User not found";
        }
        if (vaccineCenterOptional.isEmpty()) {
            return "Vaccine Center doesn't exist";
        }

        User patient = patientOptional.get();
        VaccineCenter vaccineCenter = vaccineCenterOptional.get();

        Date date = Date.valueOf(vaccineAppointmentCreateDto.getDate());
        Time time = Time.valueOf(vaccineAppointmentCreateDto.getTime());

        List<TimeSlot> timeSlots = timeSlotRepository.findByDateTimeCenter(date, time, vaccineCenter.getId());

        TimeSlot timeSlot = new TimeSlot();
        String method;
        if (timeSlots.isEmpty()) {
            timeSlot = createTimeSlot(date, time, vaccineCenter);
            method = "new timeslot";
        } else {
            timeSlot = timeSlots.get(0);
            long id = timeSlot.getId();
            int noOfAppointments = timeSlot.getNoOfAppointments() + 1;
            boolean full = noOfAppointments > 5;
            timeSlotRepository.updateAppointmentsAndFullById(id, noOfAppointments, full);
            method = "existing timeslot";
        }

        VaccineAppointment vaccineAppointment = new VaccineAppointment(vaccineAppointmentCreateDto);
        vaccineAppointment.setTimeSlot(timeSlot);
        vaccineAppointment.setFulfilled(false);
        vaccineAppointment.setPatient(patient);
        vaccineAppointmentRepository.save(vaccineAppointment);

        patient.setAppointed(true);
        userRepository.save(patient);

        return "Ok - " + method;
    }

    public TimeSlot createTimeSlot(Date date, Time time, VaccineCenter vaccineCenter) {

        TimeSlot newTimeSlot = new TimeSlot();
        newTimeSlot.setDate(date);
        newTimeSlot.setTime(time);
        newTimeSlot.setVaccineCenter(vaccineCenter);
        newTimeSlot.setNoOfAppointments(1);
        newTimeSlot.setFull(false);

        timeSlotRepository.save(newTimeSlot);

        return newTimeSlot;
    }

    public List<VaccineAppointmentDto> getAppointments(long patientId) {

        Optional<User> patientOptional = userRepository.findById(patientId);

        if (patientOptional.isEmpty()) {
            return null;
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
}
