package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineAppointmentCreateDto;
import com.vaccin.vaccin.dto.VaccineAppointmentDto;
import com.vaccin.vaccin.model.VaccineAppointment;
import com.vaccin.vaccin.service.VaccineAppointmentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class VaccineAppointmentController {

    private VaccineAppointmentService vaccineAppointmentService;

    @Autowired
    public VaccineAppointmentController( VaccineAppointmentService vaccineAppointmentService) {
        this.vaccineAppointmentService = vaccineAppointmentService;
    }

    @PostMapping("/appointments/add")
    public ResponseEntity addAppointment(@RequestBody VaccineAppointmentCreateDto vaccineAppointmentCreateDto) {

        if (vaccineAppointmentService.appointUser(vaccineAppointmentCreateDto) != null) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/appointments/{patientId}")
    public ResponseEntity<List<VaccineAppointmentDto>> getAppointments(@PathVariable long patientId) {

        List<VaccineAppointmentDto> appointments = vaccineAppointmentService.getAppointments(patientId);
        if (appointments == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (appointments.isEmpty()) {
            return new ResponseEntity<>(appointments, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
