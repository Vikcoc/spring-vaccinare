package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineAppointmentCreateDto;
import com.vaccin.vaccin.dto.VaccineAppointmentDto;
import com.vaccin.vaccin.exception.*;
import com.vaccin.vaccin.service.VaccineAppointmentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class VaccineAppointmentController {

    private VaccineAppointmentService vaccineAppointmentService;

    @Autowired
    public VaccineAppointmentController( VaccineAppointmentService vaccineAppointmentService) {
        this.vaccineAppointmentService = vaccineAppointmentService;
    }

    @PostMapping("/appointments/add")
    public ResponseEntity<List<VaccineAppointmentDto>> addAppointment
            (@RequestBody VaccineAppointmentCreateDto vaccineAppointmentCreateDto) throws BadRequestException {

        List<VaccineAppointmentDto> vaccineAppointmentDtoList =
            vaccineAppointmentService.appointUser(vaccineAppointmentCreateDto);
        return new ResponseEntity<>(vaccineAppointmentDtoList, HttpStatus.CREATED);
    }

    @GetMapping("/appointments/{patientId}")
    public ResponseEntity<List<VaccineAppointmentDto>> getAppointments(@PathVariable long patientId) throws NotFoundException {

        List<VaccineAppointmentDto> appointments = vaccineAppointmentService.getAppointments(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @DeleteMapping("/appointments/cancel/{patientId}")
    public ResponseEntity<Object> cancelAppointments(@PathVariable long patientId) throws BadRequestException {

        vaccineAppointmentService.deleteAppointments(patientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @PatchMapping("/appointments/fulfilled/{appointmentId}")
    public ResponseEntity<VaccineAppointmentDto> markAppointmentFulfilled(@PathVariable long appointmentId) throws BadRequestException {

        VaccineAppointmentDto vaccineAppointmentDto = vaccineAppointmentService.fulfillAppointment(appointmentId);
        return new ResponseEntity<>();
    }
}
