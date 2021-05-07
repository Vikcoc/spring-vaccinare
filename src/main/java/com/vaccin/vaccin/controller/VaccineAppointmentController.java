package com.vaccin.vaccin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccineAppointmentController {

    @RequestMapping("/appointments/add/")
    public String addAppointment() {

        return "Appointment added";
    }
}
