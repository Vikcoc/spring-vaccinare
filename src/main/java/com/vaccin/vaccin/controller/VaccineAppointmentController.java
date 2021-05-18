package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineAppointmentCreateDto;
import com.vaccin.vaccin.dto.VaccineAppointmentDto;
import com.vaccin.vaccin.model.VaccineAppointment;
import com.vaccin.vaccin.service.VaccineAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VaccineAppointmentController {

    private VaccineAppointmentService vaccineAppointmentService;

    @Autowired
    public VaccineAppointmentController( VaccineAppointmentService vaccineAppointmentService) {
        this.vaccineAppointmentService = vaccineAppointmentService;
    }

    @PostMapping("/appointments/add")
    public String addAppointment(@RequestBody VaccineAppointmentCreateDto vaccineAppointmentCreateDto) {

        return vaccineAppointmentService.appointUser(vaccineAppointmentCreateDto);

    }

    @GetMapping("/appointments/{patientId}")
    public List<VaccineAppointmentDto> getAppointments(@PathVariable long patientId) {

        return vaccineAppointmentService.getAppointments(patientId);
    }
}
