package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.TimeSlotDto;
import com.vaccin.vaccin.exception.NotFoundException;
import com.vaccin.vaccin.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeSlotController {

    private TimeSlotService timeSlotService;

    @Autowired
    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping("/timeslot/{timeSlotId}")
    public TimeSlotDto getTimeSlot(@PathVariable long timeSlotId) throws NotFoundException {

        return timeSlotService.getTimeSlot(timeSlotId);
    }
}