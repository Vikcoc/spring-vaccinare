package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineAppointmentCreateDto;
import com.vaccin.vaccin.service.VaccineAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccineAppointmentController {

    private VaccineAppointmentService vaccineAppointmentService;

    @Autowired
    public VaccineAppointmentController( VaccineAppointmentService vaccineAppointmentService) {
        this.vaccineAppointmentService = vaccineAppointmentService;
    }

    @PostMapping("/appointments/add")
    public String addAppointment(@RequestBody VaccineAppointmentCreateDto vaccineAppointmentCreateDto) {

        return vaccineAppointmentService.addAppointment(vaccineAppointmentCreateDto);

    }
}
