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
    public ResponseEntity<List<VaccineAppointmentDto>> addAppointment
            (@RequestBody VaccineAppointmentCreateDto vaccineAppointmentCreateDto) {

        try {
            List<VaccineAppointmentDto> vaccineAppointmentDtoList =
                vaccineAppointmentService.appointUser(vaccineAppointmentCreateDto);
            return new ResponseEntity<>(vaccineAppointmentDtoList, HttpStatus.CREATED);
        } catch (Exception exception) {
            if (exception.getMessage().equals("User not found")
                    || exception.getMessage().equals("Vaccine Center not found")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
    }

    @GetMapping("/appointments/{patientId}")
    public ResponseEntity<List<VaccineAppointmentDto>> getAppointments(@PathVariable long patientId) {

        try {
            List<VaccineAppointmentDto> appointments = vaccineAppointmentService.getAppointments(patientId);
            if (appointments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
