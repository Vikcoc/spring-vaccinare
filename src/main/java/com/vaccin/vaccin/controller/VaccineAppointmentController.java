package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.VaccineAppointmentCreateDto;
import com.vaccin.vaccin.service.VaccineAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccineAppointmentController {

    @Autowired
    private VaccineAppointmentService vaccineAppointmentService;

    @RequestMapping("/appointments/add/")
    public String addAppointment(@RequestBody VaccineAppointmentCreateDto vaccineAppointmentCreateDto) {

        return vaccineAppointmentService.addAppointment(vaccineAppointmentCreateDto);

    }
}
