package com.vaccin.vaccin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TimeSlotController {


    // facem asa?

    @RequestMapping("{center}/appointments/")
    public String seeTimeSlots() {


        return "Got time slots";
    }
}
