package com.vaccin.vaccin.dto;

import com.vaccin.vaccin.model.TimeSlot;
import com.vaccin.vaccin.model.User;

public class VaccineAppointmentCreateDto {

    private TimeSlot timeSlot;

    private User patient;

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }
}
